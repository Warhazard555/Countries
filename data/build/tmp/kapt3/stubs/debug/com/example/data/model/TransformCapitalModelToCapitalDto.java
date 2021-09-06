package com.example.data.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0005R2\u0010\u0006\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00020\u0007X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/example/data/model/TransformCapitalModelToCapitalDto;", "Lcom/example/domain/outcome/Transformer;", "", "Lcom/example/data/model/CapitalModel;", "Lcom/example/domain/dto/CapitalDto;", "()V", "convert", "Lkotlin/Function1;", "getConvert", "()Lkotlin/jvm/functions/Function1;", "setConvert", "(Lkotlin/jvm/functions/Function1;)V", "data_debug"})
public final class TransformCapitalModelToCapitalDto implements com.example.domain.outcome.Transformer<java.util.List<? extends com.example.data.model.CapitalModel>, java.util.List<? extends com.example.domain.dto.CapitalDto>> {
    @org.jetbrains.annotations.NotNull
    private kotlin.jvm.functions.Function1<? super java.util.List<com.example.data.model.CapitalModel>, ? extends java.util.List<com.example.domain.dto.CapitalDto>> convert;
    
    public TransformCapitalModelToCapitalDto() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlin.jvm.functions.Function1<java.util.List<com.example.data.model.CapitalModel>, java.util.List<com.example.domain.dto.CapitalDto>> getConvert() {
        return null;
    }
    
    @java.lang.Override
    public void setConvert(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.util.List<com.example.data.model.CapitalModel>, ? extends java.util.List<com.example.domain.dto.CapitalDto>> p0) {
    }
}