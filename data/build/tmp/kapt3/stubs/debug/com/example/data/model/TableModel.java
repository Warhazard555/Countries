package com.example.data.model;

import java.lang.System;

@androidx.room.Entity(tableName = "Country")
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\tH\u00c6\u0003J\u0006\u0010\u001b\u001a\u00020\u001cJE\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\tH\u00d6\u0001J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\n\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011\u00a8\u0006#"}, d2 = {"Lcom/example/data/model/TableModel;", "", "name", "", "capital", "area", "", "flag", "population", "", "currentDistance", "(Ljava/lang/String;Ljava/lang/String;FLjava/lang/String;II)V", "getArea", "()F", "getCapital", "()Ljava/lang/String;", "getCurrentDistance", "()I", "getFlag", "getName", "getPopulation", "component1", "component2", "component3", "component4", "component5", "component6", "convertTableModelToDto", "Lcom/example/domain/dto/CountryItemDto;", "copy", "equals", "", "other", "hashCode", "toString", "data_debug"})
public final class TableModel {
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    @androidx.room.PrimaryKey
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String capital = null;
    @androidx.room.ColumnInfo
    private final float area = 0.0F;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String flag = null;
    @androidx.room.ColumnInfo
    private final int population = 0;
    @androidx.room.ColumnInfo
    private final int currentDistance = 0;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.data.model.TableModel copy(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String capital, float area, @org.jetbrains.annotations.NotNull
    java.lang.String flag, int population, int currentDistance) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public TableModel(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String capital, float area, @org.jetbrains.annotations.NotNull
    java.lang.String flag, int population, int currentDistance) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCapital() {
        return null;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final float getArea() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getFlag() {
        return null;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int getPopulation() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int getCurrentDistance() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.domain.dto.CountryItemDto convertTableModelToDto() {
        return null;
    }
}