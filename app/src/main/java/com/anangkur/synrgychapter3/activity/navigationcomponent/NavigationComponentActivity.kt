package com.anangkur.synrgychapter3.activity.navigationcomponent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
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
    private val model = NavigationComponentModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(viewBinding.root)

        setupNavigationComponentWithAppBar()

        val data = model.fetchData()
    }

    /**
     * Sets up navigation component with the app bar for the current activity.
     *
     * This function finds the NavHostFragment associated with the navigation host fragment container,
     * retrieves the NavController from the NavHostFragment, and sets up the app bar with the NavController.
     * If the NavHostFragment is not found, the function returns without performing any action.
     */
    private fun setupNavigationComponentWithAppBar() {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment? ?: return
        setupActionBarWithNavController(host.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        return host.navController.navigateUp() || super.onSupportNavigateUp()
    }
}