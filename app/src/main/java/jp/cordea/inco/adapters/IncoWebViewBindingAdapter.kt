package jp.cordea.inco.adapters

import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.databinding.InverseBindingMethod
import android.databinding.InverseBindingMethods
import jp.cordea.inco.IncoWebView

@BindingMethods(
        BindingMethod(type = IncoWebView::class,
                attribute = "url",
                method = "loadUrl"
        ),
        BindingMethod(type = IncoWebView::class,
                attribute = "query",
                method = "setQuery"
        ),
        BindingMethod(type = IncoWebView::class,
                attribute = "setOnLoadFinishedListener",
                method = "setOnLoadFinishedListener"
        ),
        BindingMethod(type = IncoWebView::class,
                attribute = "refreshing",
                method = "setRefreshing"
        )
)
@InverseBindingMethods(
        InverseBindingMethod(type = IncoWebView::class,
                attribute = "refreshing",
                method = "getRefreshing",
                event = "refreshingAttrChanged")
)
object IncoWebViewBindingAdapter
