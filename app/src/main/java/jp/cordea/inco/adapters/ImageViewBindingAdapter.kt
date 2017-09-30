package jp.cordea.inco.adapters

import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.widget.ImageView

@BindingMethods(
        BindingMethod(type = ImageView::class,
                attribute = "src",
                method = "setImageResource"
        )
)
class ImageViewBindingAdapter
