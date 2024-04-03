package com.anangkur.synrgychapter3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anangkur.synrgychapter3.databinding.ActivityThirdBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

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

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

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