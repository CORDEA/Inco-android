package jp.cordea.inco.viewmodels

import android.content.Context
import android.content.Intent
import android.widget.AdapterView
import jp.cordea.inco.R
import jp.cordea.inco.activities.PageElementsActivity
import jp.cordea.inco.adapters.BindingListAdapter

class PageElementsViewModel(context: Context, intent: Intent) {

    companion object {
        private val UrlsKey = "UrlsKey"

        fun createIntent(context: Context, urls: List<String>) =
                Intent(context, PageElementsActivity::class.java).apply {
                    putExtra(UrlsKey, urls.toTypedArray())
                }
    }

    private val urls = intent.getStringArrayExtra(UrlsKey)?.toList() ?: listOf()

    private val items = urls.map {
        PageElementsItemViewModel(it, {
            context.startActivity(RuleViewModel.createIntent(context, it))
        })
    }

    val adapter = BindingListAdapter(context, R.layout.list_item_page_elements, items)

    val onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
        items[i].onClick()
    }
}
