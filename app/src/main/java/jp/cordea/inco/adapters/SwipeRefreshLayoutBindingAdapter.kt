package jp.cordea.inco.adapters

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.support.v4.widget.SwipeRefreshLayout

object SwipeRefreshLayoutBindingAdapter {

    @JvmStatic
    @InverseBindingAdapter(attribute = "refreshing", event = "refreshingAttrChanged")
    fun getRefreshing(layout: SwipeRefreshLayout): Boolean = layout.isRefreshing

    @JvmStatic
    @BindingAdapter("refreshingAttrChanged")
    fun refreshingAttrChanged(layout: SwipeRefreshLayout, listener: InverseBindingListener) {
        layout.setOnRefreshListener {
            listener.onChange()
        }
    }
}
