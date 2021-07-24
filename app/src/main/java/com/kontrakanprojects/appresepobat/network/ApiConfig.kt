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
        const val URL = "http://192.168.43.174:8080"

        private const val ENDPOINT =
            "${com.kontrakanprojects.appresepobat.network.ApiConfig.Companion.URL}/api/"

        private fun client(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }

        fun getApiService(): com.kontrakanprojects.appresepobat.network.ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(com.kontrakanprojects.appresepobat.network.ApiConfig.Companion.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(com.kontrakanprojects.appresepobat.network.ApiConfig.Companion.client())
                .build()
            return retrofit.create(com.kontrakanprojects.appresepobat.network.ApiService::class.java)
        }
    }
}