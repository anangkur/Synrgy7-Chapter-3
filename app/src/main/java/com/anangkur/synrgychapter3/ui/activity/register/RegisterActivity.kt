package com.anangkur.synrgychapter3.ui.activity.register

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.synrgychapter3.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<RegisterViewModel> {
        RegisterViewModel.provideFactory(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        viewModel.loading.observe(this) {
            binding.viewFlipper.displayedChild = if (it) 1 else 0
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.token.observe(this) {
            Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.buttonRegister.setOnClickListener {
            viewModel.register(
                binding.etUsername.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }
}