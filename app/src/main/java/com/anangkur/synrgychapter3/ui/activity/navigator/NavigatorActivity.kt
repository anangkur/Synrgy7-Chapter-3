package com.anangkur.synrgychapter3.ui.activity.navigator

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.synrgychapter3.ui.activity.MainActivity
import com.anangkur.synrgychapter3.ui.activity.login.LoginActivity

class NavigatorActivity : AppCompatActivity() {

    private val viewModel by viewModels<NavigatorViewModel> {
        NavigatorViewModel.provideFactory(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (viewModel.checkLogin()) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}