package com.droidtechlab.shaaditestui.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    const val BASE_URL = "https://randomuser.me/"
    var retrofit: Retrofit? = null

    var httpBuilder = OkHttpClient.Builder()
    val logging =  HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    // OkHttpClient. Be conscious with the order
    var okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder() //httpLogging interceptor for logging network requests
        .addInterceptor(logging)
        .build()

    val apiClient: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}