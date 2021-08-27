package com.example.data.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003J\u0017\u0010\u0004\u001a\u00028\u00012\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000H&\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/data/model/Transformer;", "InputType", "OutputType", "", "transform", "item", "(Ljava/lang/Object;)Ljava/lang/Object;", "data_debug"})
public abstract interface Transformer<InputType extends java.lang.Object, OutputType extends java.lang.Object> {
    
    public abstract OutputType transform(@org.jetbrains.annotations.Nullable
    InputType item);
}