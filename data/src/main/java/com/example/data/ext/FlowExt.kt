package com.example.task1.ext

import com.example.outcome.Outcome
import com.example.outcome.Transformer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.*

fun <InputType, OutputType> modifyFlow(
    data: Flow<InputType>,
    transformer: Transformer<InputType, OutputType>
): Flow<Outcome<OutputType>> {
    return data.execute(transformer.convert)
}

fun <InputType, OutputType> Flow<InputType>.execute(convert: (InputType) -> OutputType): Flow<Outcome<OutputType>> =
    this.flowOn(Dispatchers.IO)
        .map { dto -> convert(dto) }
        .map { list -> Outcome.success(list) }
        .onStart { emit(Outcome.loading(true)) }
        .onCompletion { emit(Outcome.loading(false)) }
        .catch { ex -> emit(Outcome.failure(ex)) }