package com.anangkur.synrgychapter3.ui.activity.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.synrgychapter3.MyApplication
import com.anangkur.synrgychapter3.databinding.ActivityMainBinding
import com.anangkur.synrgychapter3.di.factory.ViewModelFactory
import com.anangkur.synrgychapter3.ui.activity.blur.BlurActivity
import com.anangkur.synrgychapter3.ui.activity.imagehandler.ImageHandlerActivity
import com.anangkur.synrgychapter3.ui.activity.location.LocationActivity
import com.anangkur.synrgychapter3.ui.activity.webview.WebViewActivity
import com.anangkur.synrgychapter3.ui.activity.main2.MainActivity2
import com.anangkur.synrgychapter3.ui.activity.second.SecondActivity
import com.anangkur.synrgychapter3.ui.activity.login.LoginActivity
import com.anangkur.synrgychapter3.ui.activity.mvvm.MvvmActivity
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.NavigationComponentActivity
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent2.NavigationComponent2Activity
import com.anangkur.synrgychapter3.ui.activity.pdfhandler.PDFHandlerActivity
import com.anangkur.synrgychapter3.ui.activity.third.ThirdActivity
import com.anangkur.synrgychapter3.ui.activity.videohandler.VideoHandlerActivity
import com.anangkur.synrgychapter3.ui.dataclass.DataParcelable
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }

    private val secondActivityResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ::handleSecondActivityResultCallback,
    )

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.button1.setOnClickListener { openSecondActivity() }
        activityMainBinding.button2.setOnClickListener { openThirdActivity() }
        activityMainBinding.buttonNavigationComponent.setOnClickListener { openNavigationComponentActivity() }
        activityMainBinding.buttonNavigationComponent2.setOnClickListener { openNavigationComponentActivity2() }
        activityMainBinding.buttonDataBinding.setOnClickListener { openDataBinding() }
        activityMainBinding.buttonMvvm.setOnClickListener { openMvvm() }
        activityMainBinding.buttonLogout.setOnClickListener { logout() }
        activityMainBinding.buttonWebview.setOnClickListener { openWebView() }
        activityMainBinding.buttonLocation.setOnClickListener { openLocation() }
        activityMainBinding.buttonImageHandler.setOnClickListener { openImageHandler() }
        activityMainBinding.buttonPdfHandler.setOnClickListener { openPdfHandler() }
        activityMainBinding.buttonVideoHandler.setOnClickListener { openVideoHandler() }
        activityMainBinding.buttonBlur.setOnClickListener { openBlur() }
        activityMainBinding.buttonCrash.setOnClickListener { triggerCrash() }

        Log.d("MainActivity", "lifecycle state: onCreate")

        setupToolbar()

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "lifecycle state: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "lifecycle state: onResume")
        refresh()
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "lifecycle state: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "lifecycle state: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "lifecycle state: onDestroy")
    }

    private fun refresh() {
        Log.d("MainActivity", "refresh product data")
    }

    private fun setupToolbar() {
        Log.d("MainActivity", "setup toolbar")
    }

    /**
     * Handles the result callback from the SecondActivity.
     * If the result code indicates success, retrieves the result string from the Intent extras,
     * then displays it using a Snackbar.
     *
     * @param callback The activity result containing the result code and data.
     */
    private fun handleSecondActivityResultCallback(callback: ActivityResult) {
        if (callback.resultCode == Activity.RESULT_OK) {
            val result = callback.data?.getStringExtra(SecondActivity.EXTRA_STRING)
            result?.let {
                Snackbar.make(activityMainBinding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Opens the SecondActivity and passes data to it using different methods.
     *
     * Currently, it demonstrates passing Parcelable data to SecondActivity using ActivityResultLauncher.
     * You can also find commented-out code snippets demonstrating other methods like passing Serializable data,
     * passing data through Bundle, and passing data directly as extras in the Intent.
     *
     * Uncomment and use the appropriate method as needed based on the data type and requirements.
     */
    private fun openSecondActivity() {
        val parcelable = DataParcelable("ini adalah data parcelable", 100)
        secondActivityResult.launch(
            SecondActivity.provideIntent(this)
                .putExtra(SecondActivity.EXTRA_PARCELABLE, parcelable)
        )
        // val dataSerializable = DataSerializable("ini adalah data serializable", 100)
        // SecondActivity.startActivity(this, dataSerializable)
        // val bundle = Bundle().apply {
        //     putString(SecondActivity.EXTRA_STRING, "ini adalah data bundle")
        //     putInt(SecondActivity.EXTRA_INTEGER, 100)
        // }
        // SecondActivity.startActivity(this, bundle)
        // SecondActivity.startActivity(this, "ini adalah data", 100)
        // Snackbar.make(activityMainBinding.root, "test button 1", Snackbar.LENGTH_SHORT).show()
    }

    /**
     * Opens the ThirdActivity.
     *
     * This function starts the ThirdActivity by calling its startActivity method,
     * without passing any additional data.
     */
    private fun openThirdActivity() {
        ThirdActivity.startActivity(this)
    }

    /**
     * Opens the NavigationComponentActivity.
     *
     * This function starts the NavigationComponentActivity by calling its startActivity method,
     * without passing any additional data.
     */
    private fun openNavigationComponentActivity() {
        NavigationComponentActivity.startActivity(this)
    }

    /**
     * Opens the NavigationComponentActivity.
     *
     * This function starts the NavigationComponentActivity by calling its startActivity method,
     * without passing any additional data.
     */
    private fun openNavigationComponentActivity2() {
        NavigationComponent2Activity.startActivity(this)
    }

    private fun openDataBinding() {
        startActivity(Intent(this, MainActivity2::class.java))
    }

    private fun openMvvm() {
        startActivity(Intent(this, MvvmActivity::class.java))
    }

    private fun logout() {
        viewModel.logout()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun openWebView() {
        startActivity(Intent(this, WebViewActivity::class.java))
    }

    private fun openLocation() {
        startActivity(Intent(this, LocationActivity::class.java))
    }

    private fun openImageHandler() {
        startActivity(Intent(this, ImageHandlerActivity::class.java))
    }

    private fun openPdfHandler() {
        startActivity(Intent(this, PDFHandlerActivity::class.java))
    }

    private fun openVideoHandler() {
        startActivity(Intent(this, VideoHandlerActivity::class.java))
    }

    private fun openBlur() {
        startActivity(Intent(this, BlurActivity::class.java))
    }

    private fun triggerCrash() {
        throw RuntimeException("test crashlytics")
    }
}