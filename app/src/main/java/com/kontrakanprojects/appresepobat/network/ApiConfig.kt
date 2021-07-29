package com.kontrakanprojects.appresepobat.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        // local url
        //192.168.43.174:8080
        //"http://127.0.0.1:8080"
        const val URL = "https://resepobat.rproject-dev.com"

        private const val ENDPOINT =
            "${URL}/api_wcbr/api/"
        const val ENDPOINT_IMAGES = "$URL/"

        private fun client(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }

        fun getApiService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}