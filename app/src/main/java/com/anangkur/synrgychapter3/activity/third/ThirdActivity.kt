package com.anangkur.synrgychapter3.activity.third

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anangkur.synrgychapter3.activity.third.subthirdactivity.first.FirstFragment
import com.anangkur.synrgychapter3.activity.third.subthirdactivity.ForthFragment
import com.anangkur.synrgychapter3.R
import com.anangkur.synrgychapter3.activity.third.subthirdactivity.SecondFragment
import com.anangkur.synrgychapter3.activity.third.subthirdactivity.ThirdFragment
import com.anangkur.synrgychapter3.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ThirdActivity::class.java))
        }
    }

    private val viewBinding: ActivityThirdBinding by lazy { ActivityThirdBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(viewBinding.root)
        handleBottomNavigation()
        viewBinding.bottomNav.selectedItemId = R.id.menu_1
    }

    /**
     * Sets the provided fragment to the container within the activity.
     *
     * This function replaces the content of the fragment container with the provided fragment.
     * It performs a fragment transaction using the supportFragmentManager to replace the existing fragment
     * with the new one and commits the transaction.
     *
     * @param fragment The fragment to be set within the activity.
     */
    private fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    /**
     * Sets up the bottom navigation view and handles item selection.
     *
     * This function configures the bottom navigation view by setting a listener for item selection.
     * When an item is selected, it replaces the content of the fragment container based on the selected item.
     * It uses the setFragment function to replace the fragment accordingly.
     */
    private fun handleBottomNavigation() {
        viewBinding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_1 -> {
                    setFragment(FirstFragment())
                    true
                }
                R.id.menu_2 -> {
                    setFragment(SecondFragment.newInstance("data string", 1000))
                    true
                }
                R.id.menu_3 -> {
                    setFragment(ThirdFragment())
                    true
                }
                R.id.menu_4 -> {
                    setFragment(ForthFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}