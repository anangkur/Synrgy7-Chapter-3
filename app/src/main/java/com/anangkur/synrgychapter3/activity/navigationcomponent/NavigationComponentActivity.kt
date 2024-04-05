package com.anangkur.synrgychapter3.activity.navigationcomponent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.anangkur.synrgychapter3.R
import com.anangkur.synrgychapter3.databinding.ActivityNavigationComponentBinding

class NavigationComponentActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, NavigationComponentActivity::class.java))
        }
    }

    private val viewBinding by lazy { ActivityNavigationComponentBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(viewBinding.root)

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment? ?: return
        setupActionBarWithNavController(host.navController)
    }
}