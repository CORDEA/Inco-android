package jp.cordea.inco.viewmodels

import jp.cordea.inco.models.Rule

data class BlacklistItemViewModel(
        val title: String,
        val onClick: () -> Unit,
        val onLongClick: () -> Unit
) {
    constructor(rule: Rule, onClick: () -> Unit, onLongClick: () -> Unit)
            : this(rule.regex, onClick, onLongClick)
}
