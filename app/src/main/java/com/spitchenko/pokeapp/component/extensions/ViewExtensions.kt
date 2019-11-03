package com.spitchenko.pokeapp.component.extensions

import android.view.View
import androidx.navigation.findNavController

fun View.initNavigateUpClickListener() =
    setOnClickListener {
        it.findNavController().navigateUp()
    }