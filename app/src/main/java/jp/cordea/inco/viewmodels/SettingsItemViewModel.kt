package jp.cordea.inco.viewmodels

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.support.annotation.StringRes
import jp.cordea.inco.BR

class SettingsItemViewModel(
        @StringRes titleResource: Int,
        val onClick: (SettingsItemViewModel) -> Unit
) : BaseObservable() {

    @Bindable
    var titleResource = titleResource
        private set

    fun refreshTitle(titleResource: Int) {
        this.titleResource = titleResource
        notifyPropertyChanged(BR.titleResource)
    }
}
