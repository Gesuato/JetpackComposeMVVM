package com.example.jetpackcomposemvvm.remote.retrofit

import com.example.jetpackcomposemvvm.BuildConfig
import com.example.jetpackcomposemvvm.remote.api.ApiConstants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RetrofitInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val requestBuilder = chain.request().newBuilder()
            val originalUrl = chain.request().url
            val updatedUrl = originalUrl.newBuilder()
                .addQueryParameter(ApiConstants.API_KEY, BuildConfig.API_KEY)
                .build()
            requestBuilder.url(updatedUrl)
            return chain.proceed(requestBuilder.build())
        } catch (e: Exception) {
            throw  IOException(e)
        }
    }
}