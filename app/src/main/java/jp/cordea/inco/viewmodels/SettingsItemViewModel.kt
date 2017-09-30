package jp.cordea.inco.viewmodels

import android.support.annotation.StringRes

data class SettingsItemViewModel(
        @StringRes val titleResource: Int,
        val onClick: () -> Unit
)
