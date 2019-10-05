package com.spitchenko.pokeapp.component.navigation

import android.content.Context
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.spitchenko.pokeapp.di.InjectingFragmentFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class InjectingNavHostFragment : NavHostFragment() {

    @Inject
    lateinit var daggerFragmentInjectionFactory: InjectingFragmentFactory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        childFragmentManager.fragmentFactory = daggerFragmentInjectionFactory
        super.onCreate(savedInstanceState)
    }
}