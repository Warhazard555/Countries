package com.example.task1.base.mvi

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import org.koin.androidx.scope.ScopeFragment

abstract class BaseFragmentMVI<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState,
        VM :BaseViewModelMVI<INTENT, ACTION, STATE>> :
    IRender<STATE>, ScopeFragment() {

    private lateinit var viewState: STATE
    val mState get() = viewState

    private val viewModel: VM by lazy {
        ViewModelProvider(
            this.viewModelFactory,
            modelClass.kotlin
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initUI()
        viewModel.state.observe(this, {
            viewState = it
            render(it)
        })
        initData()
    }

    abstract fun initData()
    abstract fun initUI()
    fun dispatchIntent(intent: INTENT) {
        viewModel.dispatchIntent(intent)
    }
}

