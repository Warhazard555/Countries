package com.example.data.retrofit

import com.example.data.BASE_URL
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CoroutinesRetrofitService {

    companion object {
        private var instance: CoroutinesRetrofitInterface? = null

        fun getInstance(): CoroutinesRetrofitInterface {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val log: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            if (instance == null)
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(log)
                    .build()
                    .create(CoroutinesRetrofitInterface::class.java)

            return CoroutinesRetrofitService.instance as CoroutinesRetrofitInterface

        }
    }
}