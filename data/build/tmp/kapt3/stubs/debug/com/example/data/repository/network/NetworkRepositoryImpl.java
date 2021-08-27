package com.example.data.repository.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/example/data/repository/network/NetworkRepositoryImpl;", "Lcom/example/domain/repository/NetworkRepository;", "retrofitService", "Lcom/example/data/retrofit/RetrofitInterface;", "(Lcom/example/data/retrofit/RetrofitInterface;)V", "getCountryByName", "Lio/reactivex/rxjava3/core/Flowable;", "", "Lcom/example/domain/dto/CountryItemDto;", "name", "", "getData", "data_debug"})
public final class NetworkRepositoryImpl implements com.example.domain.repository.NetworkRepository {
    private final com.example.data.retrofit.RetrofitInterface retrofitService = null;
    
    public NetworkRepositoryImpl(@org.jetbrains.annotations.NotNull
    com.example.data.retrofit.RetrofitInterface retrofitService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public io.reactivex.rxjava3.core.Flowable<java.util.List<com.example.domain.dto.CountryItemDto>> getData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public io.reactivex.rxjava3.core.Flowable<java.util.List<com.example.domain.dto.CountryItemDto>> getCountryByName(@org.jetbrains.annotations.NotNull
    java.lang.String name) {
        return null;
    }
}