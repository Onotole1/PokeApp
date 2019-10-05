package com.spitchenko.pokeapp.component.network.di

import com.spitchenko.pokeapp.BuildConfig
import com.spitchenko.pokeapp.component.di.ApplicationScope
import com.spitchenko.pokeapp.component.log.info
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providePokemonRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .applyLoggingForDebug()
        .build()

    private fun OkHttpClient.Builder.applyLoggingForDebug(): OkHttpClient.Builder =
        if (BuildConfig.DEBUG) {
            addNetworkInterceptor(getHttpLoggingInterceptor())
        } else {
            this
        }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(getLoggingInterceptorLogger())
            .apply { level = HttpLoggingInterceptor.Level.BODY }

    private fun getLoggingInterceptorLogger() = object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            info(message)
        }
    }
}