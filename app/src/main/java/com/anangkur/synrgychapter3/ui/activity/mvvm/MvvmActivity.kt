package com.anangkur.synrgychapter3.ui.activity.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.synrgychapter3.MyApplication
import com.anangkur.synrgychapter3.databinding.ActivityMvvmBinding
import org.koin.android.ext.android.inject

class MvvmActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMvvmBinding.inflate(layoutInflater) }
    private val viewModel: MvvmViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        viewModel.liveData.observe(this) { data ->
            binding.text.text = data.toString()
        }

        binding.buttonIncrement.setOnClickListener {
            viewModel.incrementData()
        }
    }
}