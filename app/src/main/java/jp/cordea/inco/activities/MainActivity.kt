package jp.cordea.inco.activities

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import jp.cordea.inco.R
import jp.cordea.inco.databinding.ActivityMainBinding
import jp.cordea.inco.viewmodels.HistoryViewModel
import jp.cordea.inco.viewmodels.MainViewModel
import jp.cordea.inco.viewmodels.PageElementsViewModel
import jp.cordea.inco.viewmodels.SettingsViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private val HistoryRequestCode = 1

        fun finishActivity(activity: Activity) {
            activity.finishActivity(HistoryRequestCode)
        }
    }

    private val viewModel by lazy {
        MainViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
                .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = viewModel
        setSupportActionBar(binding.toolbar)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            HistoryRequestCode -> {
                if (resultCode == Activity.RESULT_OK) {
                    viewModel.loadUrl(data)
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_detail -> {
            startActivity(PageElementsViewModel.createIntent(this, viewModel.urls))
            true
        }
        R.id.action_settings -> {
            startActivity(SettingsViewModel.createIntent(this))
            true
        }
        R.id.action_history -> {
            startActivity(HistoryViewModel.createIntent(this))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
