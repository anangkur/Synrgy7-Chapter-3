package com.anangkur.synrgychapter3

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.synrgychapter3.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.button1.setOnClickListener { openSecondActivity() }
        activityMainBinding.button2.setOnClickListener { openThirdActivity() }

        Log.d("MainActivity", "lifecycle state: onCreate")

        setupToolbar()
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

    private fun handleSecondActivityResultCallback(callback: ActivityResult) {
        if (callback.resultCode == Activity.RESULT_OK) {
            val result = callback.data?.getStringExtra(SecondActivity.EXTRA_STRING)
            result?.let {
                Snackbar.make(activityMainBinding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

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

    private fun openThirdActivity() {
        ThirdActivity.startActivity(this)
    }
}