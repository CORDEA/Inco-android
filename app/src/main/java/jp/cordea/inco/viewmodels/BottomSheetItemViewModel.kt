package jp.cordea.inco.viewmodels

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.view.View

class BottomSheetItemViewModel(
        @StringRes val titleResource: Int,
        @DrawableRes val iconResource: Int,
        val onClick: View.OnClickListener
)
