package jp.cordea.inco.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.cordea.inco.R
import jp.cordea.inco.databinding.ActivitySignInBinding
import jp.cordea.inco.viewmodels.SignInViewModel

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySignInBinding>(this, R.layout.activity_sign_in)
        binding.vm = SignInViewModel(this)
        setSupportActionBar(binding.toolbar)
    }

}
