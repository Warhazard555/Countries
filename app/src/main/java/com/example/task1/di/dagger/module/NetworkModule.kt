package com.example.task1.di.dagger.module

import android.content.Context
import com.chenxyu.retrofit.adapter.FlowCallAdapterFactory
import com.example.data.BASE_URL_NEWS
import com.example.data.retrofit.NewsRetrofitInterface
import com.example.task1.THOUSAND
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesOkhttpCache(context: Context): Cache {
        return Cache(context.cacheDir, 1024)
    }

    @Provides
    @Singleton
    fun providesOkhttpClient(cache: Cache): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(6, TimeUnit.SECONDS)
            .writeTimeout(6, TimeUnit.SECONDS)
            .readTimeout(6, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor(interceptor)
        return client.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_NEWS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): NewsRetrofitInterface {
        return retrofit.create(NewsRetrofitInterface::class.java)
    }
}