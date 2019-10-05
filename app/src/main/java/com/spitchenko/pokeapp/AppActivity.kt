package com.spitchenko.pokeapp

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

class AppActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}
