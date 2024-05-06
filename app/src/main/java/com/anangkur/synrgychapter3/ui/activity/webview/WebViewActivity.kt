package com.anangkur.synrgychapter3.ui.activity.webview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.synrgychapter3.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {

    private val binding: ActivityWebviewBinding by lazy {
        ActivityWebviewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.root.loadUrl("https://google.com")
    }
}