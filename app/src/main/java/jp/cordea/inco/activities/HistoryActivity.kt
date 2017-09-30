package jp.cordea.inco.activities

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import jp.cordea.inco.R
import jp.cordea.inco.databinding.ActivityHistoryBinding
import jp.cordea.inco.viewmodels.HistoryViewModel

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityHistoryBinding>(this, R.layout.activity_history)
        binding.vm = HistoryViewModel(this) {
            setResult(Activity.RESULT_OK, it)
            MainActivity.finishActivity(this)
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
