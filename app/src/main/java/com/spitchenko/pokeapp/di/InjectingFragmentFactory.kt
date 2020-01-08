package com.spitchenko.pokeapp.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.spitchenko.pokeapp.component.di.FragmentComponent
import com.spitchenko.pokeapp.component.log.warning
import javax.inject.Inject
import javax.inject.Provider

class InjectingFragmentFactory @Inject constructor(
    private val providers: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<FragmentComponent.Factory<*>>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        val creator = providers[fragmentClass]
            ?: return createFragmentAsFallback(classLoader, className)

        return creator.get().create().fragment()
    }

    private fun createFragmentAsFallback(classLoader: ClassLoader, className: String): Fragment {
        warning("No creator found for class: $className. Using default constructor")
        return super.instantiate(classLoader, className)
    }
}