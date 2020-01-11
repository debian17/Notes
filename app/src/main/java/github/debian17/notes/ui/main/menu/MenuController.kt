package github.debian17.notes.ui.main.menu

import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import github.debian17.notes.R

class MenuController(
    private val toolbar: Toolbar,
    private val menuAdapter: MenuAdapter,
    private val drawerLayout: DrawerLayout
) {

    fun setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

}