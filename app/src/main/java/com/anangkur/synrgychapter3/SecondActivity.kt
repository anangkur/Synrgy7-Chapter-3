package com.anangkur.synrgychapter3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.anangkur.synrgychapter3.databinding.ActivitySecondBinding
import java.io.Serializable

class SecondActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_STRING = "extraStringString"
        const val EXTRA_INTEGER = "extraInteger"
        private const val EXTRA_BUNDLE = "extraBundle"
        private const val EXTRA_SERIALIZABLE = "extraSerializable"
        private const val EXTRA_PARCELABLE = "extraParcelable"

        fun startActivity(context: Context, extraString: String, extraInteger: Int) {
            context.startActivity(
                Intent(context, SecondActivity::class.java)
                    .putExtra(EXTRA_STRING, extraString)
                    .putExtra(EXTRA_INTEGER, extraInteger)
            )
        }

        fun startActivity(context: Context, bundle: Bundle) {
            context.startActivity(
                Intent(context, SecondActivity::class.java)
                    .putExtra(EXTRA_BUNDLE, bundle)
            )
        }

        fun startActivity(context: Context, data: Serializable) {
            context.startActivity(
                Intent(context, SecondActivity::class.java)
                    .putExtra(EXTRA_SERIALIZABLE, data)
            )
        }

        fun startActivity(context: Context, data: Parcelable) {
            context.startActivity(
                Intent(context, SecondActivity::class.java)
                    .putExtra(EXTRA_PARCELABLE, data)
            )
        }
    }

    private val activitySecondBinding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activitySecondBinding.rootSecond)

        val bundle = intent?.getBundleExtra(EXTRA_BUNDLE)
        val serializable: DataSerializable? = intent?.getSerializableExtra(EXTRA_SERIALIZABLE) as DataSerializable?
        val parcelable: DataParcelable? = intent?.getParcelableExtra(EXTRA_PARCELABLE)

        val extraString = when {
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

        val extraInteger = when {
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

        activitySecondBinding.tvSecond.text = "$extraString $extraInteger"
    }
}