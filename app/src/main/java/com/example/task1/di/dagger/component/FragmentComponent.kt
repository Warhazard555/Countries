package com.example.task1.di.dagger.component

import com.example.task1.base.mvi.RootBaseFragment
import com.example.task1.di.dagger.annotations.FragmentScope
import com.example.task1.di.dagger.common.AppRouter
import com.example.task1.di.dagger.module.FragmentModule
import dagger.Component

@FragmentScope
@Component(modules = [FragmentModule::class], dependencies = [ApplicationComponent::class])
interface FragmentComponent {
    fun inject(baseFragment: RootBaseFragment)
    fun appRouter(): AppRouter
}