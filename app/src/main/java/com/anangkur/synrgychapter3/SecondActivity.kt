package com.anangkur.synrgychapter3

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.synrgychapter3.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable

class SecondActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_STRING = "extraStringString"
        const val EXTRA_INTEGER = "extraInteger"
        private const val EXTRA_BUNDLE = "extraBundle"
        private const val EXTRA_SERIALIZABLE = "extraSerializable"
        const val EXTRA_PARCELABLE = "extraParcelable"

        private const val REQUEST_IMAGE_CAPTURE = 1

        fun startActivity(context: Context, extraString: String, extraInteger: Int) {
            context.startActivity(
                provideIntent(context)
                    .putExtra(EXTRA_STRING, extraString)
                    .putExtra(EXTRA_INTEGER, extraInteger)
            )
        }

        fun startActivity(context: Context, bundle: Bundle) {
            context.startActivity(
                provideIntent(context)
                    .putExtra(EXTRA_BUNDLE, bundle)
            )
        }

        fun startActivity(context: Context, data: Serializable) {
            context.startActivity(
                provideIntent(context)
                    .putExtra(EXTRA_SERIALIZABLE, data)
            )
        }

        fun startActivity(context: Context, data: Parcelable) {
            context.startActivity(
                provideIntent(context)
                    .putExtra(EXTRA_PARCELABLE, data)
            )
        }

        fun provideIntent(context: Context): Intent {
            return Intent(context, SecondActivity::class.java)
        }
    }

    private val activitySecondBinding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activitySecondBinding.rootSecond)

        activitySecondBinding.buttonTakePicture.setOnClickListener { dispatchTakePictureIntent() }
        setDataFromPreviousActivityToTextView(getDataFromPreviousActivity())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                Log.d("SecondActivity", data?.data?.toString().orEmpty().ifEmpty { "data nya kosong" })
            }
            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    override fun onBackPressed() {
        val intentResult = Intent().putExtra(EXTRA_STRING, activitySecondBinding.tvSecond.text)
        setResult(Activity.RESULT_OK, intentResult)
        super.onBackPressed()
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            e.message?.let {
                Snackbar.make(activitySecondBinding.rootSecond, it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDataFromPreviousActivity(): String {
        val bundle = intent?.getBundleExtra(EXTRA_BUNDLE)
        val serializable: DataSerializable? = intent?.getSerializableExtra(EXTRA_SERIALIZABLE) as DataSerializable?
        val parcelable: DataParcelable? = intent?.getParcelableExtra(EXTRA_PARCELABLE)

        val extraString = getExtraStringFromPreviousActivity(bundle, serializable, parcelable)
        val extraInteger = getExtraIntegerFromPreviousActivity(bundle, serializable, parcelable)

        return "$extraString $extraInteger"
    }

    private fun getExtraStringFromPreviousActivity(
        bundle: Bundle?,
        serializable: DataSerializable?,
        parcelable: DataParcelable?,
    ): String? {
        return when {
            bundle != null -> {
                bundle.getString(EXTRA_STRING)
            }
            serializable != null -> {
                serializable.string
            }
            parcelable != null -> {
                parcelable.string
            }
            else -> {
                intent?.getStringExtra(EXTRA_STRING)
            }
        }
    }

    private fun getExtraIntegerFromPreviousActivity(
        bundle: Bundle?,
        serializable: DataSerializable?,
        parcelable: DataParcelable?,
    ): Int? {
        return when {
            bundle != null -> {
                bundle.getInt(EXTRA_INTEGER)
            }
            serializable != null -> {
                serializable.integer
            }
            parcelable != null -> {
                parcelable.integer
            }
            else -> {
                intent?.getIntExtra(EXTRA_INTEGER, -1)
            }
        }
    }

    private fun setDataFromPreviousActivityToTextView(data: String) {
        activitySecondBinding.tvSecond.text = data
    }
}