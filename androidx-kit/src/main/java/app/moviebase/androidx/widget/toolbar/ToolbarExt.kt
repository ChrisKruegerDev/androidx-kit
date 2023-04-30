package app.moviebase.androidx.widget.toolbar

import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI

fun Toolbar.setOnMenuItemClick(@MenuRes menuRes: Int, onMenuItemClick: (Int) -> Unit) {
    menu.clear()
    inflateMenu(menuRes)
    setOnMenuItemClickListener { onMenuItemClick(it.itemId); true }
}

fun Toolbar.setupWithNavController(navController: NavController) =
        NavigationUI.setupWithNavController(this, navController)
