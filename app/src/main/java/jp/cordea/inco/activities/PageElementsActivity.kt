package jp.cordea.inco.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import jp.cordea.inco.R
import jp.cordea.inco.databinding.ActivityPageElementsBinding
import jp.cordea.inco.viewmodels.PageElementsViewModel

class PageElementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityPageElementsBinding>(this, R.layout.activity_page_elements)
        binding.vm = PageElementsViewModel(this, intent)
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
