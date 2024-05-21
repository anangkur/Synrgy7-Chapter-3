package com.anangkur.synrgychapter3.ui.activity.navigator

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.synrgychapter3.MyApplication
import com.anangkur.synrgychapter3.ui.activity.main.MainActivity
import com.anangkur.synrgychapter3.ui.activity.login.LoginActivity

class NavigatorActivity : AppCompatActivity() {

    private val viewModel by viewModels<NavigatorViewModel> {
        (application as MyApplication).viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isLoggedIn.observe(this) { isLoggedIn ->
            if (isLoggedIn) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }

        viewModel.checkLogin()
    }
}