package com.picpay.desafio.android.repository.remote

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.util.Client
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {


    companion object {
        var instance: Retrofit
        private const val BASE_URL: String = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

        init {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(Client.okHttp)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()
                    )
                )
                .build()
        }
    }
}
