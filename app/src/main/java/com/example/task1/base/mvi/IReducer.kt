package com.example.task1.base.mvi

interface IReducer <STATE, T :Any> {
    fun reduce(result: Result<T>, state: STATE,): STATE
}