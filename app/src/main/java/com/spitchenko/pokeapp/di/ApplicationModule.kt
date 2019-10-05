package com.spitchenko.pokeapp.di

import com.spitchenko.pokeapp.AppActivity
import com.spitchenko.pokeapp.component.di.ActivityScope
import com.spitchenko.pokeapp.component.network.di.NetworkModule
import com.spitchenko.pokeapp.feature.list.data.di.PokemonListDataModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(
	includes = [
		NetworkModule::class,
		PicassoModule::class,
		PokemonListDataModule::class,
		AndroidSupportInjectionModule::class
	]
)
interface ApplicationModule {

	@ActivityScope
	@ContributesAndroidInjector(
		modules = [
			AppActivityInjectModule::class
		]
	)
	fun appActivityInjector(): AppActivity
}