package jp.cordea.inco.viewmodels

import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.support.design.widget.BottomSheetBehavior
import android.view.View
import android.widget.AdapterView
import jp.cordea.inco.BR
import jp.cordea.inco.Key
import jp.cordea.inco.R
import jp.cordea.inco.activities.HistoryActivity
import jp.cordea.inco.adapters.BindingListAdapter
import jp.cordea.inco.repositories.HistoryRepository
import kotlinx.coroutines.experimental.runBlocking

class HistoryViewModel(
        context: Context,
        onRequestPage: (Intent) -> Unit
) : BaseObservable() {

    companion object {
        fun createIntent(context: Context): Intent =
                Intent(context, HistoryActivity::class.java)
    }

    private val key = Key.getCipherKey(context)

    private val nonce = Key.getNonce(context)

    val items = arrayListOf<HistoryItemViewModel>()

    private var selectedPosition = -1

    @Bindable
    var bottomSheetTitle = ""

    val bottomSheetItem = BottomSheetItemViewModel(
            R.string.history_delete,
            R.drawable.ic_delete_black_24dp,
            View.OnClickListener {
                runBlocking {
                    val item = items[selectedPosition]
                    HistoryRepository.deleteHistory(item.title).await()
                    items.remove(item)
                    adapter.setItems(items)
                    selectedPosition = -1
                }

                state = BottomSheetBehavior.STATE_HIDDEN
                notifyPropertyChanged(BR.state)
            }
    )

    @Bindable
    var state = BottomSheetBehavior.STATE_HIDDEN

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
        runBlocking {
            items.addAll(
                    HistoryRepository.getHistories().await().map {
                        val title = HistoryRepository.decryptUrl(it.url, key, nonce)
                        HistoryItemViewModel(title, it, {
                            onRequestPage(MainViewModel.createIntent(it.url))
                        }, {
                            state = BottomSheetBehavior.STATE_COLLAPSED
                            bottomSheetTitle = it.url
                            notifyPropertyChanged(BR.state)
                            notifyPropertyChanged(BR.bottomSheetTitle)
                        })
                    })
            adapter.setItems(items)
        }
    }
}
