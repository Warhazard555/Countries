package com.example.task1.di

import com.example.data.model.CapitalModel
import com.example.data.model.TransformCapitalModelToCapitalDto
import com.example.data.repository.database.DataBaseRepositoryImpl
//import com.example.data.repository.network.NetworkCapitalFlowRepositoryImpl
import com.example.data.repository.network.NetworkCapitalRepositoryImpl
import com.example.data.repository.network.NetworkRepositoryImpl
import com.example.data.retrofit.CoroutinesRetrofitService
import com.example.data.retrofit.FlowRetrofitService
import com.example.data.retrofit.RetrofitService
import com.example.data.room.CountryDatabase
import com.example.domain.dto.CapitalDto
import com.example.domain.repository.DataBaseRepository
import com.example.domain.repository.NetworkCapitalRepository
import com.example.domain.repository.NetworkCapitalsFlowRepository
import com.example.domain.repository.NetworkRepository
import org.koin.dsl.module
import javax.xml.transform.Transformer

val appModule = module {
    //Model
    single { CountryDatabase.getDatabaseInst(get()) }
    single { RetrofitService.getInstance() }
    single { CoroutinesRetrofitService.getInstance() }
    single { FlowRetrofitService.getInstance() }

    //Data
    single<NetworkRepository> { NetworkRepositoryImpl(get()) }
    single<DataBaseRepository> { DataBaseRepositoryImpl(get()) }
    single<NetworkCapitalRepository> { NetworkCapitalRepositoryImpl(get()) }
//    single<NetworkCapitalsFlowRepository> { NetworkCapitalFlowRepositoryImpl(get(),get()) }

}