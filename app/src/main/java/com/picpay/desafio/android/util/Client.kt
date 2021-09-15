package com.picpay.desafio.android.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object Client {

    val okHttp: OkHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}