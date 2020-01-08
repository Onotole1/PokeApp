package com.spitchenko.pokeapp.component.di

import androidx.fragment.app.Fragment

interface FragmentComponent<T : Fragment> {

    fun fragment(): T

    interface Factory<T : FragmentComponent<out Fragment>> {

        fun create(): T
    }
}