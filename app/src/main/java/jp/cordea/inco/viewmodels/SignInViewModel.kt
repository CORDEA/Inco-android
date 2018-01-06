package jp.cordea.inco.viewmodels

import android.content.Context
import android.view.View
import jp.cordea.inco.Key
import jp.cordea.inco.api.ApiClient
import kotlinx.coroutines.experimental.async
import ru.gildor.coroutines.retrofit.await

class SignInViewModel(context: Context) {

    var password: String = ""

    var username: String = ""

    val onClick = View.OnClickListener {
        if (username.isBlank() || password.isBlank()) {
            return@OnClickListener
        }

        async {
            val token = ApiClient.login(username, password).await()
            if (token.token.isBlank()) {
                return@async
            }
            Key.token = token.token
            context.startActivity(MainViewModel.createIntent(context))
        }
    }
}
