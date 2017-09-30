package jp.cordea.inco.viewmodels

import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.support.design.widget.BottomSheetBehavior
import android.view.View
import android.widget.AdapterView
import jp.cordea.inco.BR
import jp.cordea.inco.R
import jp.cordea.inco.activities.BlacklistActivity
import jp.cordea.inco.adapters.BindingListAdapter
import jp.cordea.inco.repositories.BlacklistRepository
import kotlinx.coroutines.experimental.runBlocking

class BlacklistViewModel(context: Context) : BaseObservable() {

    companion object {
        fun createIntent(context: Context): Intent =
                Intent(context, BlacklistActivity::class.java)
    }

    var items = arrayListOf<BlacklistItemViewModel>()

    val adapter = BindingListAdapter(context, R.layout.list_item_blacklist, items)

    private var selectedPosition = -1

    @Bindable
    var bottomSheetTitle = ""

    val editBottomSheetItem = BottomSheetItemViewModel(
            R.string.rule_edit,
            R.drawable.ic_edit_black_24dp,
            View.OnClickListener {
                val item = items[selectedPosition]
                context.startActivity(RuleViewModel.createIntent(context, item.title))
                adapter.setItems(items)
                selectedPosition = -1

                state = BottomSheetBehavior.STATE_HIDDEN
                notifyPropertyChanged(BR.state)
            }
    )


    val deleteBottomSheetItem = BottomSheetItemViewModel(
            R.string.rule_delete,
            R.drawable.ic_delete_black_24dp,
            View.OnClickListener {
                runBlocking {
                    val item = items[selectedPosition]
                    BlacklistRepository.deleteRule(item.title).await()
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

    val onClick = View.OnClickListener {
        context.startActivity(RuleViewModel.createIntent(context))
    }

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
            items.addAll(BlacklistRepository.getRules().await().map {
                BlacklistItemViewModel(it, {
                }, {
                    state = BottomSheetBehavior.STATE_COLLAPSED
                    bottomSheetTitle = it.regex
                    notifyPropertyChanged(BR.state)
                    notifyPropertyChanged(BR.bottomSheetTitle)
                })
            })
            adapter.setItems(items)
        }
    }
}
