package com.example.task1.data

interface Transformer <InputType, OutputType> {
    fun transform(item: InputType?) : OutputType
}