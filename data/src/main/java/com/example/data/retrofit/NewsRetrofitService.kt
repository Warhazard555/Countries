package com.example.data.retrofit

import com.example.data.BASE_URL_NEWS
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRetrofitService {

    companion object {
        private var instance: NewsRetrofitInterface? = null

        fun getInstance(): NewsRetrofitInterface {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val log: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            if (instance == null)
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .baseUrl(BASE_URL_NEWS)
                    .client(log)
                    .build()
                    .create(NewsRetrofitInterface::class.java)

            return instance as NewsRetrofitInterface

        }
    }
}