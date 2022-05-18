package com.example.jetpackcomposemvvm.injection

import android.content.Context
import com.example.jetpackcomposemvvm.BuildConfig
import com.example.jetpackcomposemvvm.remote.api.ApiConstants
import com.example.jetpackcomposemvvm.remote.api.GameApi
import com.example.jetpackcomposemvvm.remote.retrofit.RetrofitInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun providesGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    fun providesRetrofitInterceptor() = RetrofitInterceptor()

    @Provides
    fun providesCache(context: Context): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(context.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Provides
    fun providesOkHttpClient(retrofitInterceptor: RetrofitInterceptor, cache: Cache): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addNetworkInterceptor(loggingInterceptor)
        }
        okHttpClient.addInterceptor(retrofitInterceptor)
        okHttpClient.cache(cache)
        return okHttpClient.build()
    }

    @Provides
    fun providesRetrofitApi(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideGameApi(retrofit: Retrofit): GameApi = retrofit.create()
}