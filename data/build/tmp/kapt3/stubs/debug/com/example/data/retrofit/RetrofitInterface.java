package com.example.data.retrofit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\'J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'\u00a8\u0006\t"}, d2 = {"Lcom/example/data/retrofit/RetrofitInterface;", "", "getCountryByName", "Lio/reactivex/rxjava3/core/Flowable;", "", "Lcom/example/domain/dto/CountryItemDto;", "name", "", "getData", "data_debug"})
public abstract interface RetrofitInterface {
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "all")
    public abstract io.reactivex.rxjava3.core.Flowable<java.util.List<com.example.domain.dto.CountryItemDto>> getData();
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "name/{path}")
    public abstract io.reactivex.rxjava3.core.Flowable<java.util.List<com.example.domain.dto.CountryItemDto>> getCountryByName(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Path(value = "path")
    java.lang.String name);
}