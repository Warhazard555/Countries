package com.example.outcome

interface Transformer<InputType, OutputType> {
    var convert: (InputType) -> OutputType
}