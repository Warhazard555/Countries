package com.example.task1.di

import com.example.task1.fragments.map.MapsFragment
import com.example.task1.fragments.map.MapsFragmentPresenter
import org.koin.dsl.module

val mapModule = module { scope<MapsFragment> { scoped { MapsFragmentPresenter(get()) } } }