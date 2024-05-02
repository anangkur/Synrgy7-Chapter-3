package com.anangkur.synrgychapter3.ui.activity.navigationcomponent2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.anangkur.synrgychapter3.R
import com.anangkur.synrgychapter3.databinding.ActivityNavigationComponent2Binding
import com.anangkur.synrgychapter3.ui.dataclass.Movie

class NavigationComponent2Activity : AppCompatActivity(), NavigationComponent2ViewContract {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, NavigationComponent2Activity::class.java))
        }
    }

    private val viewBinding by lazy { ActivityNavigationComponent2Binding.inflate(layoutInflater) }
    private val presenter = NavigationComponent2Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        setupNavigationComponentWithAppBar()
        presenter.fetchData()
    }

    /**
     * Sets up navigation component with the app bar for the current activity.
     *
     * This function finds the NavHostFragment associated with the navigation host fragment container,
     * retrieves the NavController from the NavHostFragment, and sets up the app bar with the NavController.
     * If the NavHostFragment is not found, the function returns without performing any action.
     */
    private fun setupNavigationComponentWithAppBar() {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment? ?: return
        setupActionBarWithNavController(host.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        return host.navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDataReceived(data: List<Movie>) {
        Toast.makeText(this, "data diterima: ${data.size} todal data", Toast.LENGTH_SHORT).show()
    }

    override fun onDataError(error: Throwable) {
        Toast.makeText(this, "data error: ${error.message}", Toast.LENGTH_SHORT).show()
    }

    override fun onLoading(isLoading: Boolean) {
        if (isLoading){
            Toast.makeText(this, "loading...", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "loading selesai", Toast.LENGTH_SHORT).show()
        }
    }
}