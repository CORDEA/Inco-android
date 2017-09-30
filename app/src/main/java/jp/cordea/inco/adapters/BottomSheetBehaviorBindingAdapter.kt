package jp.cordea.inco.adapters

import android.databinding.BindingAdapter
import android.support.design.widget.BottomSheetBehavior
import android.view.View

object BottomSheetBehaviorBindingAdapter {

    @JvmStatic
    @BindingAdapter("state")
    fun setState(view: View, state: Int) {
        val behavior = BottomSheetBehavior.from(view)
        behavior.state = state
    }

}
