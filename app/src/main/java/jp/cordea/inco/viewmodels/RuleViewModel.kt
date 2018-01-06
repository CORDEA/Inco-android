package jp.cordea.inco.viewmodels

import android.content.Context
import android.content.Intent
import android.view.View
import jp.cordea.inco.activities.RuleActivity
import jp.cordea.inco.models.Rule
import jp.cordea.inco.repositories.BlacklistRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class RuleViewModel(intent: Intent, onFinish: () -> Unit) {

    companion object {
        private val RegexKey = "RegexKey"

        fun createIntent(context: Context, regex: String = ""): Intent =
                Intent(context, RuleActivity::class.java).apply {
                    if (regex.isNotBlank()) {
                        putExtra(RegexKey, regex)
                    }
                }
    }

    val onClick = View.OnClickListener {
        if (regex.isBlank()) {
            return@OnClickListener
        }

        launch(UI) {
            BlacklistRepository.updateRule(Rule(regex)).await()
            onFinish()
        }
    }

    var regex = intent.getStringExtra(RegexKey) ?: ""

}
