package com.example.data.retrofit

import com.chenxyu.retrofit.adapter.FlowCallAdapterFactory
import com.example.data.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlowRetrofitService {

    companion object{
        private var instance: FlowRetrofitInterface? = null

        fun getInstance(): FlowRetrofitInterface{
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val log: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            if (instance == null)
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(FlowCallAdapterFactory())
                    .baseUrl(BASE_URL)
                    .client(log)
                    .build()
                    .create(FlowRetrofitInterface::class.java)
            return FlowRetrofitService.instance as FlowRetrofitInterface
        }
    }
}