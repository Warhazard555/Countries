package com.example.task1.base.mvi

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.data.model.TransformNewsDataToNewsDto
import com.example.task1.CountryApp
import com.example.task1.di.dagger.common.AppRouter
import com.example.task1.di.dagger.component.DaggerFragmentComponent
import com.example.task1.di.dagger.component.FragmentComponent
import com.example.task1.di.dagger.module.FragmentModule
import com.example.task1.di.dagger.viewModels.DaggerViewModelFactory
import javax.inject.Inject

open class RootBaseFragment: Fragment() {

    private val fragmentComponent: FragmentComponent by lazy {
        DaggerFragmentComponent.builder().fragmentModule(FragmentModule(this))
            .applicationComponent(CountryApp.appComponents).build()
    }

    @Inject
    lateinit var appRouter: AppRouter

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    @Inject
    lateinit var transformer: TransformNewsDataToNewsDto

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentComponent.inject(this)
        super.onCreate(savedInstanceState)
    }
}