package jp.cordea.inco

import android.content.Context
import android.databinding.InverseBindingListener
import android.graphics.Bitmap
import android.util.AttributeSet
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import jp.cordea.inco.repositories.BlacklistRepository
import jp.cordea.inco.repositories.HistoryRepository
import kotlinx.coroutines.experimental.runBlocking
import java.io.ByteArrayInputStream

class IncoWebView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    companion object {
        private val GoogleSearchUrl = "https://www.google.co.jp/search?q=%s"
    }

    interface OnLoadFinishedListener {
        fun onLoadFinished(urls: List<String>)
    }

    private var listener: OnLoadFinishedListener? = null

    private val rules = BlacklistRepository.getRules()

    private val key = Key.getCipherKey(context)

    private var nonce = Key.getNonce(context)

    private var refreshingListener: InverseBindingListener? = null

    fun setRefreshing(refreshing: Boolean) {
        if (refreshing) {
            reload()
            refreshingListener?.onChange()
        }
    }

    fun getRefreshing(): Boolean = false

    fun setRefreshingAttrChanged(listener: InverseBindingListener) {
        refreshingListener = listener
    }

    init {
        if (nonce.isBlank()) {
            nonce = HistoryRepository.refreshNonce(context)
        }
        settings.apply {
            javaScriptEnabled = Key.javaScriptEnabled(context)
            domStorageEnabled = false
            setAppCacheEnabled(false)
        }
        webViewClient = object : WebViewClient() {
            private val urls = arrayListOf<String>()

            override fun shouldInterceptRequest(
                    view: WebView, request: WebResourceRequest): WebResourceResponse? {
                val url = request.url.toString()
                var isMatch = false
                return runBlocking {
                    rules.await().forEach {
                        val reg = it.regex.toRegex()
                        if (reg.containsMatchIn(url)) {
                            isMatch = true
                            return@forEach
                        }
                    }
                    if (isMatch) {
                        val st = ByteArrayInputStream(ByteArray(0))
                        WebResourceResponse("text/html", "utf-8", st)
                    } else {
                        urls.add(url)
                        null
                    }
                }
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                urls.clear()
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)

                HistoryRepository.updateHistory(url, key, nonce).invokeOnCompletion {
                    it?.printStackTrace()
                }
                listener?.onLoadFinished(urls)
            }
        }
    }

    fun setOnLoadFinishedListener(listener: OnLoadFinishedListener) {
        this.listener = listener
    }

    fun setQuery(query: String) {
        if (query.isBlank()) {
            return
        }
        loadUrl(GoogleSearchUrl.format(query))
    }
}
