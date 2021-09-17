package com.example.task1.di.dagger.module

import android.content.Context
import com.example.data.model.TransformNewsDataToNewsDto
import com.example.data.repository.network.NetworkNewsRepositoryImpl
import com.example.data.retrofit.NewsRetrofitInterface
import com.example.domain.repository.NetworkNewsRepository
import com.example.task1.CountryApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import javax.xml.transform.Transformer

@Module
class ApplicationModule {
    @Provides
    fun provideApplicationContext(application : CountryApp) : Context = application.applicationContext

    @Provides
    @Singleton
    fun provideNewsRepository(apiService: NewsRetrofitInterface,
    transformer: TransformNewsDataToNewsDto): NetworkNewsRepository{
        return NetworkNewsRepositoryImpl(apiService,transformer)
    }
}