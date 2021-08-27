package com.example.domain.useCase

import io.reactivex.rxjava3.core.Flowable
import java.io.Serializable

abstract class BaseUseCase<Params : Any, Result> : Serializable {

    private var mParams: Params? = null

    protected abstract fun buildFlowable(params: Params?): Flowable<Result>

    abstract val mIsParamsRequired: Boolean

    fun setParams(params: Params): BaseUseCase<Params, Result> {
        mParams = params
        return this
    }

    fun execute(): Flowable<Result> {
        require(!(mIsParamsRequired && mParams == null)) { "Params are required" }
        return buildFlowable(mParams)
    }

}