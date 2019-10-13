package com.spitchenko.pokeapp.component.extensions

import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

fun Toolbar.initToolbar(navController: NavController) {
    val appBarConfiguration = AppBarConfiguration(navController.graph)

    setupWithNavController(navController, appBarConfiguration)
}