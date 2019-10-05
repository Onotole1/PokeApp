package com.spitchenko.pokeapp.di

import android.content.Context
import com.spitchenko.pokeapp.PokeApplication
import com.spitchenko.pokeapp.component.di.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@Component(modules = [ApplicationModule::class])
@ApplicationScope
interface ApplicationComponent : AndroidInjector<PokeApplication> {

	@Component.Factory
	interface Factory {

		fun create(@BindsInstance appContext: Context): ApplicationComponent
	}
}