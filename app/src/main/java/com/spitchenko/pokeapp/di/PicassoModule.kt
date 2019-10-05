package com.spitchenko.pokeapp.di

import android.content.Context
import com.jakewharton.picasso.OkHttp3Downloader
import com.spitchenko.pokeapp.component.di.ApplicationScope
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class PicassoModule {

    @Provides
    @ApplicationScope
    fun providePicasso(context: Context, okHttpClient: OkHttpClient): Picasso =
        Picasso.Builder(context)
            .downloader(OkHttp3Downloader(okHttpClient))
            .build()
}