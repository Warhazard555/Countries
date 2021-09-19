package com.example.task1.base.mvi

import android.os.Bundle
import android.view.View

abstract class BaseFragmentMVI<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState,
        VM : BaseViewModelMVI<INTENT, ACTION, STATE>>(private val modelClass: Class<VM>) :
    IRender<STATE>, RootBaseFragment() {

    private lateinit var viewState: STATE
    val mState get() = viewState

    private val viewModel: VM by lazy {
        viewModelProvider(this.viewModelFactory, modelClass.kotlin)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        viewModel.state.observe(viewLifecycleOwner, {
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

