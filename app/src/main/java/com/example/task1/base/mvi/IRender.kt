package com.example.task1.base.mvi

interface IRender<STATE> {
    fun render(state: STATE)
}
