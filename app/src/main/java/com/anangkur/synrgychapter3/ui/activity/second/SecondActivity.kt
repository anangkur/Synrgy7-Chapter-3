package com.anangkur.synrgychapter3.ui.activity.second

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
import com.anangkur.synrgychapter3.ui.dataclass.DataParcelable
import com.anangkur.synrgychapter3.ui.dataclass.DataSerializable
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

        /**
         * Starts the activity with provided context, extra string, and extra integer.
         *
         * This function creates an intent using the provided context and adds extraString and extraInteger
         * as extras to the intent. Then it starts the activity using the created intent.
         *
         * @param context The context from which the activity should be started.
         * @param extraString The extra string data to be passed to the activity.
         * @param extraInteger The extra integer data to be passed to the activity.
         */
        fun startActivity(context: Context, extraString: String, extraInteger: Int) {
            context.startActivity(
                provideIntent(context)
                    .putExtra(EXTRA_STRING, extraString)
                    .putExtra(EXTRA_INTEGER, extraInteger)
            )
        }

        /**
         * Starts the activity with provided context and bundle.
         *
         * This function creates an intent using the provided context and adds the bundle
         * as an extra to the intent. Then it starts the activity using the created intent.
         *
         * @param context The context from which the activity should be started.
         * @param bundle The bundle containing additional data to be passed to the activity.
         */
        fun startActivity(context: Context, bundle: Bundle) {
            context.startActivity(
                provideIntent(context)
                    .putExtra(EXTRA_BUNDLE, bundle)
            )
        }

        /**
         * Starts the activity with provided context and serializable data.
         *
         * This function creates an intent using the provided context and adds the serializable data
         * as an extra to the intent. Then it starts the activity using the created intent.
         *
         * @param context The context from which the activity should be started.
         * @param data The serializable data to be passed to the activity.
         */
        fun startActivity(context: Context, data: Serializable) {
            context.startActivity(
                provideIntent(context)
                    .putExtra(EXTRA_SERIALIZABLE, data)
            )
        }

        /**
         * Starts the activity with provided context and parcelable data.
         *
         * This function creates an intent using the provided context and adds the parcelable data
         * as an extra to the intent. Then it starts the activity using the created intent.
         *
         * @param context The context from which the activity should be started.
         * @param data The parcelable data to be passed to the activity.
         */
        fun startActivity(context: Context, data: Parcelable) {
            context.startActivity(
                provideIntent(context)
                    .putExtra(EXTRA_PARCELABLE, data)
            )
        }

        /**
         * Creates and returns an Intent for starting the SecondActivity.
         *
         * This function takes a context as input and creates an Intent for starting the SecondActivity
         * by specifying the context and the class of the activity to be started.
         *
         * @param context The context used to create the Intent.
         * @return An Intent for starting the SecondActivity.
         */
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

    /**
     * Callback method invoked when an activity launched by this activity exits,
     * returning the requestCode, resultCode, and any additional data from it.
     *
     * @param requestCode The integer request code originally supplied to startActivityForResult(),
     *                    identifying who the result came from.
     * @param resultCode The integer result code returned by the child activity through its setResult().
     * @param data An Intent, which can return result data to the caller (varies by requestCode).
     */
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

    /**
     * Called when the user presses the back button.
     *
     * This function prepares an Intent containing data to be sent back to the calling activity,
     * sets the result code to indicate success, and finishes the activity.
     */
    override fun onBackPressed() {
        val intentResult = Intent().putExtra(EXTRA_STRING, activitySecondBinding.tvSecond.text)
        setResult(Activity.RESULT_OK, intentResult)
        super.onBackPressed()
    }

    /**
     * Dispatches an intent to capture an image using the device's camera.
     *
     * This function creates an intent to capture an image using the device's camera app.
     * It then starts the activity for result with the created intent, using the REQUEST_IMAGE_CAPTURE request code.
     * If the device doesn't have a camera app or if it fails to resolve the intent, it shows a Snackbar with an error message.
     */
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

    /**
     * Retrieves data passed from the previous activity and constructs a string representation.
     *
     * This function extracts data from the intent extras received from the previous activity,
     * including a bundle, serializable object, and parcelable object. It then calls helper functions
     * to extract specific data components and constructs a string combining them.
     *
     * @return A string representation of the combined data retrieved from the previous activity.
     */
    private fun getDataFromPreviousActivity(): String {
        val bundle = intent?.getBundleExtra(EXTRA_BUNDLE)
        val serializable: DataSerializable? = intent?.getSerializableExtra(EXTRA_SERIALIZABLE) as DataSerializable?
        val parcelable: DataParcelable? = intent?.getParcelableExtra(EXTRA_PARCELABLE)

        val extraString = getExtraStringFromPreviousActivity(bundle, serializable, parcelable)
        val extraInteger = getExtraIntegerFromPreviousActivity(bundle, serializable, parcelable)

        return "$extraString $extraInteger"
    }

    /**
     * Retrieves the extra string data passed from the previous activity.
     *
     * This function checks various sources for the extra string data:
     *   - If a bundle is provided, it attempts to extract the string from the bundle.
     *   - If a serializable object is provided, it extracts the string from the object.
     *   - If a parcelable object is provided, it extracts the string from the object.
     *   - If none of the above sources provide the string, it attempts to extract it directly from the intent extras.
     *
     * @param bundle The bundle containing extra data passed from the previous activity.
     * @param serializable The serializable object containing extra data passed from the previous activity.
     * @param parcelable The parcelable object containing extra data passed from the previous activity.
     * @return The extra string data retrieved from the previous activity, or null if not found.
     */
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

    /**
     * Retrieves the extra integer data passed from the previous activity.
     *
     * This function checks various sources for the extra integer data:
     *   - If a bundle is provided, it attempts to extract the integer from the bundle.
     *   - If a serializable object is provided, it extracts the integer from the object.
     *   - If a parcelable object is provided, it extracts the integer from the object.
     *   - If none of the above sources provide the integer, it attempts to extract it directly from the intent extras.
     *
     * @param bundle The bundle containing extra data passed from the previous activity.
     * @param serializable The serializable object containing extra data passed from the previous activity.
     * @param parcelable The parcelable object containing extra data passed from the previous activity.
     * @return The extra integer data retrieved from the previous activity, or null if not found.
     */
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

    /**
     * Sets the provided data to the TextView in the current activity.
     *
     * This function takes a string data as input and sets it to the TextView
     * in the current activity's layout using data binding.
     *
     * @param data The data to be set to the TextView.
     */
    private fun setDataFromPreviousActivityToTextView(data: String) {
        activitySecondBinding.tvSecond.text = data
    }
}