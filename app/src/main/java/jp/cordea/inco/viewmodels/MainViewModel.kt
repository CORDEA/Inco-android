package jp.cordea.inco.viewmodels

import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.support.v7.widget.SearchView
import android.webkit.URLUtil
import jp.cordea.inco.BR
import jp.cordea.inco.IncoWebView
import jp.cordea.inco.activities.MainActivity

class MainViewModel : BaseObservable() {

    companion object {
        private val InitialLoadUrl = "https://google.com"

        private val UrlKey = "UrlKey"

        fun createIntent(url: String) =
                Intent().apply {
                    putExtra(UrlKey, url)
                }

        fun createIntent(context: Context) =
                Intent(context, MainActivity::class.java)
    }

    @Bindable
    var url: String = InitialLoadUrl

    @Bindable
    var query: String = ""

    var urls = listOf<String>()
        private set

    @Bindable
    var refreshing: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.refreshing)
        }

    val onSearchQueryTextListener =
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(text: String): Boolean {
                    if (URLUtil.isValidUrl(text)) {
                        url = text
                        notifyPropertyChanged(BR.url)
                        return true
                    }
                    query = text
                    notifyPropertyChanged(BR.query)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean = true
            }

    val onLoadFinishedListener = object : IncoWebView.OnLoadFinishedListener {
        override fun onLoadFinished(urls: List<String>) {
            this@MainViewModel.urls = urls
        }
    }

    fun loadUrl(intent: Intent?) {
        intent?.let {
            url = it.getStringExtra(UrlKey)
        }
    }
}
