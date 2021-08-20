package com.example.domain.useCase

import java.io.Serializable

abstract class CoroutionesUseCase<Params : Any, Result> : Serializable {

    private var mParams: Params? = null

    protected abstract suspend fun buildResult(params: Params?): Result

    abstract val mIsParamsRequired: Boolean

    fun setParams(params: Params): CoroutionesUseCase<Params, Result> {
        mParams = params
        return this
    }

    suspend fun execute(): Result {
        require(!(mIsParamsRequired && mParams == null)) { "Params are required" }
        return buildResult(mParams)
    }

}
