package github.debian17.notes.ui.main

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import github.debian17.notes.R
import github.debian17.notes.base.mvvm.BaseActivity
import github.debian17.notes.ui.main.menu.MenuAdapter
import github.debian17.notes.ui.main.menu.MenuController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.container_main.*

class MainActivity : BaseActivity() {

    private lateinit var menuAdapter: MenuAdapter
    private lateinit var menuController: MenuController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()

        menuAdapter = MenuAdapter()
        menuController = MenuController(
            toolbar,
            menuAdapter,
            drawer_layout
        )
        menuController.setupToolbar()

    }

    private fun setupNavigation() {
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }


}
