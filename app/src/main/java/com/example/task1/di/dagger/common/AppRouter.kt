package com.example.task1.di.dagger.common

import com.example.task1.base.mvi.RootBaseFragment
import com.example.task1.di.dagger.annotations.FragmentScope
import javax.inject.Inject

@FragmentScope
class AppRouter @Inject constructor(private val activity : RootBaseFragment){


}