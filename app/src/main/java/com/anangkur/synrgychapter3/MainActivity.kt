package com.anangkur.synrgychapter3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.synrgychapter3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.button1.setOnClickListener {
            val parcelable = DataParcelable("ini adalah data parcelable", 100)
            SecondActivity.startActivity(this, parcelable)
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
}