package com.example.task1.di

import com.example.task1.repository.database.DataBaseRepository
import com.example.task1.repository.database.DataBaseRepositoryImpl
import com.example.task1.repository.network.NetworkRepository
import com.example.task1.repository.network.NetworkRepositoryImpl
import com.example.task1.retrofit.RetrofitService
import com.example.task1.room.CountryDatabase
import org.koin.dsl.module

val appModule = module {
    //Model
    single { CountryDatabase.getDatabaseInst(get()) }
    single { RetrofitService.getInstance() }

    //Data
    single { NetworkRepositoryImpl(get()) as NetworkRepository}
    single { DataBaseRepositoryImpl(get()) as DataBaseRepository }
}