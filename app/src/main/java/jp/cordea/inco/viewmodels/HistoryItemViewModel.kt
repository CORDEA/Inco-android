package jp.cordea.inco.viewmodels

import jp.cordea.inco.models.History

class HistoryItemViewModel(
        val title: String,
        val description: String,
        val count: Int,
        val onClick: () -> Unit,
        val onLongClick: () -> Unit
) {
    constructor(
            title: String,
            history: History,
            onClick: () -> Unit,
            onLongClick: () -> Unit
    ) : this(
            title,
            history.createdAt,
            history.count,
            onClick, onLongClick
    )
}
