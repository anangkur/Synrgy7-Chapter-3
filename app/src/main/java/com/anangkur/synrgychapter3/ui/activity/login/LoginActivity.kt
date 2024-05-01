package com.anangkur.synrgychapter3.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.anangkur.synrgychapter3.data.AuthRepositoryImpl
import com.anangkur.synrgychapter3.data.datasource.local.AuthLocalDataSourceImpl
import com.anangkur.synrgychapter3.data.datasource.local.SharedPreferencesFactory
import com.anangkur.synrgychapter3.data.datasource.remote.AuthRemoteDataSourceImpl
import com.anangkur.synrgychapter3.databinding.ActivityLoginBinding
import com.anangkur.synrgychapter3.domain.AuthRepository
import com.anangkur.synrgychapter3.ui.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<LoginViewModel> {
        LoginViewModel.provideFactory(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            if (binding.tieEmail.text.isNullOrEmpty()) {
                binding.tieEmail.error = "email tidak boleh kosong"
            } else if (binding.tiePassword.text.isNullOrEmpty()) {
                binding.tiePassword.error = "password tidak boleh kosong"
            } else {
                binding.tieEmail.error = null
                binding.tiePassword.error = null
                viewModel.login(
                    username = binding.tieEmail.text.toString(),
                    password = binding.tiePassword.text.toString(),
                )
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) {
                binding.flipperButtonLogin.displayedChild = 1
            } else {
                binding.flipperButtonLogin.displayedChild = 0
            }
        }

        viewModel.success.observe(this) { isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        viewModel.error.observe(this) { throwable ->
            Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
        }
    }
}