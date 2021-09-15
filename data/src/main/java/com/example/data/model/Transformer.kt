package com.example.data.model

interface Transformer<InputType, OutputType> {
    fun transform(item: InputType?): OutputType
}