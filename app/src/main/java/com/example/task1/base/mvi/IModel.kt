package com.example.task1.base.mvi

import androidx.lifecycle.LiveData

interface IModel
    <STATE, INTENT> {

        val state: LiveData<STATE>

        fun dispatchIntent(intent: INTENT)
}