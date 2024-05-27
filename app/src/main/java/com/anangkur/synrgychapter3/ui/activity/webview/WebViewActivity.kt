package com.anangkur.synrgychapter3.ui.activity.webview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.synrgychapter3.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_URL = "url"
        fun startActivity(context: Context, url: String) {
            context.startActivity(
                Intent(context, WebViewActivity::class.java)
                    .putExtra(EXTRA_URL, url)
            )
        }
    }

    private val binding: ActivityWebviewBinding by lazy {
        ActivityWebviewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        configureWebView(intent.getStringExtra(EXTRA_URL).orEmpty().ifEmpty { "https://www.google.com" })
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configureWebView(url: String) {
        binding.webview.webViewClient = WebViewClient()
        binding.webview.settings.setSupportZoom(true)
        binding.webview.settings.domStorageEnabled = true
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl(url)
    }
}