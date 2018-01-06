package jp.cordea.inco.viewmodels

import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.widget.AdapterView
import jp.cordea.inco.R
import jp.cordea.inco.activities.HistoryActivity
import jp.cordea.inco.adapters.BindingListAdapter
import jp.cordea.inco.repositories.HistoryRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ru.gildor.coroutines.retrofit.await

class HistoryViewModel(
        context: Context,
        onRequestPage: (Intent) -> Unit
) : BaseObservable() {

    companion object {
        fun createIntent(context: Context): Intent =
                Intent(context, HistoryActivity::class.java)
    }

    private val items = arrayListOf<HistoryItemViewModel>()

    private var selectedPosition = -1

    val adapter = BindingListAdapter(context, R.layout.list_item_history, items)

    val onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
        items[i].onClick()
    }

    val onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, i, _ ->
        selectedPosition = i
        items[i].onLongClick()
        true
    }

    init {
        launch(UI) {
            val histories = HistoryRepository.getHistories().await()
            items.addAll(
                    histories.map {
                        val title = HistoryRepository.decryptUrl(context, it.url)
                        HistoryItemViewModel(title, it, {
                            onRequestPage(MainViewModel.createIntent(it.url))
                        }, {
                        })
                    })
            adapter.setItems(items)
        }
    }
}
