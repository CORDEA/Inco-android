package jp.cordea.inco.viewmodels

import android.content.Context
import android.content.Intent
import android.widget.AdapterView
import jp.cordea.inco.R
import jp.cordea.inco.activities.SettingsActivity
import jp.cordea.inco.adapters.BindingListAdapter

class SettingsViewModel(context: Context) {

    companion object {
        fun createIntent(context: Context): Intent =
                Intent(context, SettingsActivity::class.java)
    }

    private val items = listOf(
            SettingsItemViewModel(R.string.settings_blacklist) {
                context.startActivity(BlacklistViewModel.createIntent(context))
            }
    )

    val adapter = BindingListAdapter(context, R.layout.list_item_settings, items)

    val onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
        items[i].onClick()
    }
}
