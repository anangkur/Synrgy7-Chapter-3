package com.anangkur.synrgychapter3.ui.activity.pdfhandler

import android.app.Activity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.synrgychapter3.databinding.ActivityPdfHandlerBinding
import com.anangkur.synrgychapter3.ui.activity.webview.WebViewActivity
import com.nareshchocha.filepickerlibrary.models.DocumentFilePickerConfig
import com.nareshchocha.filepickerlibrary.ui.FilePicker
import com.rajat.pdfviewer.PdfViewerActivity
import com.rajat.pdfviewer.util.saveTo

class PDFHandlerActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPdfHandlerBinding.inflate(layoutInflater) }

    private val filePickerResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ::handleFilePickerResult,
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.buttonAssets.setOnClickListener {
            startActivity(
                PdfViewerActivity.launchPdfFromPath(
                    context = this,
                    path = "dummies.pdf",
                    pdfTitle = "Dummy",
                    saveTo = saveTo.ASK_EVERYTIME,
                    fromAssets = true,
                )
            )
        }
        binding.buttonWebView.setOnClickListener {
            WebViewActivity.startActivity(this, "https://docs.google.com/viewer?url=https://pii.or.id/uploads/dummies.pdf")
        }
        binding.buttonStorage.setOnClickListener {
            pickPdfFromLocalStorage()
        }
        binding.buttonInternet.setOnClickListener {
            openPdfFromInternet("https://pii.or.id/uploads/dummies.pdf")
        }
    }

    private fun pickPdfFromLocalStorage() {
        filePickerResult.launch(
            FilePicker.Builder(this)
                .pickDocumentFileBuild(
                    DocumentFilePickerConfig(
                        allowMultiple = false,
                        mMimeTypes = listOf("application/pdf"),
                    ),
                ),
        )
    }

    private fun handleFilePickerResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            startActivity(
                PdfViewerActivity.launchPdfFromPath(
                    context = this,
                    path = result.data?.data?.toString(),
                    pdfTitle = "Dummy",
                    saveTo = saveTo.ASK_EVERYTIME,
                    fromAssets = false,
                )
            )
        }
    }

    private fun openPdfFromInternet(url: String) {
        startActivity(
            PdfViewerActivity.launchPdfFromUrl(
                context = this,
                pdfUrl = url,
                pdfTitle = "Dummy",
                saveTo = saveTo.ASK_EVERYTIME,
                enableDownload = true,
            )
        )
    }
}