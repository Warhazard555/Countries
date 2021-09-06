package com.example.data.repository.database;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\u0016J\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/example/data/repository/database/DataBaseRepositoryImpl;", "Lcom/example/domain/repository/DataBaseRepository;", "dataBase", "Lcom/example/data/room/CountryDatabase;", "(Lcom/example/data/room/CountryDatabase;)V", "getAllCountryDB", "Lio/reactivex/rxjava3/core/Flowable;", "", "Lcom/example/domain/dto/CountryItemDto;", "insertDatabase", "", "list", "data_debug"})
public final class DataBaseRepositoryImpl implements com.example.domain.repository.DataBaseRepository {
    private final com.example.data.room.CountryDatabase dataBase = null;
    
    public DataBaseRepositoryImpl(@org.jetbrains.annotations.NotNull
    com.example.data.room.CountryDatabase dataBase) {
        super();
    }
    
    @java.lang.Override
    public void insertDatabase(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.domain.dto.CountryItemDto> list) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public io.reactivex.rxjava3.core.Flowable<java.util.List<com.example.domain.dto.CountryItemDto>> getAllCountryDB() {
        return null;
    }
}