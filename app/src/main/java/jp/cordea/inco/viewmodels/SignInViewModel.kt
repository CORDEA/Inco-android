package jp.cordea.inco.viewmodels

import android.content.Context
import android.view.View
import jp.cordea.inco.CipherUtils
import jp.cordea.inco.HashGenerator
import jp.cordea.inco.Key
import jp.cordea.inco.R

class SignInViewModel(context: Context) {

    private val generator = HashGenerator()

    private val savedPassword = Key.getPassword(context)

    private val isExistsPassword = savedPassword.isNotBlank()

    var password: String = ""

    val buttonText = context.resources.getString(
            if (isExistsPassword) {
                R.string.title_sign_in_button
            } else {
                R.string.title_sign_up_button
            }
    )

    val onClick = View.OnClickListener {
        if (password.isBlank()) {
            return@OnClickListener
        }
        val hash = generator.generate(password)
        if (isExistsPassword) {
            if (savedPassword == hash) {
                context.startActivity(MainViewModel.createIntent(context))
            }
            return@OnClickListener
        }
        Key.setPassword(context, hash)
        val key = CipherUtils.generateKey()
        Key.setCipherKey(context, key)
        context.startActivity(MainViewModel.createIntent(context))
    }
}