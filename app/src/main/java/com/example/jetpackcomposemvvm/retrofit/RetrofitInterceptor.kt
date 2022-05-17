package com.example.jetpackcomposemvvm.retrofit

import com.example.jetpackcomposemvvm.api.ApiConstants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RetrofitInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val requestBuilder = chain.request().newBuilder()
            val originalUrl = chain.request().url
            val updatedUrl = originalUrl.newBuilder()
                .addQueryParameter("key", ApiConstants.API_KEY)
                .build()
            requestBuilder.url(updatedUrl)
            return chain.proceed(requestBuilder.build())
        } catch (e: Exception) {
            throw  IOException(e)
        }
    }
}