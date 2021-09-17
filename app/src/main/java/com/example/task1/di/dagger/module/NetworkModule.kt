package com.example.task1.di.dagger.module

import android.content.Context
import com.example.task1.THOUSAND
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

class NetworkModule {

    @Provides
    @Singleton
    fun providesOkhttpCache(context: Context): Cache {
        return Cache(context.cacheDir, THOUSAND.toLong())
    }

    @Provides
    @Singleton
    fun providesOkhttpClient(cache: Cache): OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(6,TimeUnit.SECONDS)
            .writeTimeout(6,TimeUnit.SECONDS)
            .readTimeout(6,TimeUnit.SECONDS)
            .cache(cache)

    }
}