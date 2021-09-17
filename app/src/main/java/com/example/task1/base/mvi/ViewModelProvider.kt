package com.example.task1.base.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

fun <T : ViewModel> RootBaseFragment.viewModelProvider(
    factory: ViewModelProvider.Factory,
    model: KClass<T>
): T {
    return ViewModelProvider(this, factory).get(model.java)
}

fun Boolean.runIfTrue(block: () -> Unit) {
    if (this) {
        block()
    }
}
