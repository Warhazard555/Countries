package com.example.data.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b#\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\b\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0011J\u0011\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bH\u00c6\u0003J\u0011\u0010,\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\bH\u00c6\u0003J\u0011\u0010-\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\bH\u00c6\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010$Jt\u0010/\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00c6\u0001\u00a2\u0006\u0002\u00100J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u000104H\u00d6\u0003J\t\u00105\u001a\u00020\u000eH\u00d6\u0001J\t\u00106\u001a\u00020\u0003H\u00d6\u0001R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R&\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR&\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR&\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0016\"\u0004\b\"\u0010\u0018R\"\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\'\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&\u00a8\u00067"}, d2 = {"Lcom/example/data/model/CountryItem;", "Ljava/io/Serializable;", "name", "", "capital", "area", "", "flags", "", "languages", "Lcom/example/domain/dto/LanguageDto;", "latlng", "", "population", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;)V", "getArea", "()Ljava/lang/Float;", "setArea", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "getCapital", "()Ljava/lang/String;", "setCapital", "(Ljava/lang/String;)V", "getFlags", "()Ljava/util/List;", "setFlags", "(Ljava/util/List;)V", "getLanguages", "setLanguages", "getLatlng", "setLatlng", "getName", "setName", "getPopulation", "()Ljava/lang/Integer;", "setPopulation", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;)Lcom/example/data/model/CountryItem;", "equals", "", "other", "", "hashCode", "toString", "data_debug"})
public final class CountryItem implements java.io.Serializable {
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "name")
    private java.lang.String name;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "capital")
    private java.lang.String capital;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "area")
    private java.lang.Float area;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "flags")
    private java.util.List<java.lang.String> flags;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "languages")
    private java.util.List<com.example.domain.dto.LanguageDto> languages;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "latlng")
    private java.util.List<java.lang.Double> latlng;
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "population")
    private java.lang.Integer population;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.data.model.CountryItem copy(@org.jetbrains.annotations.Nullable
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.String capital, @org.jetbrains.annotations.Nullable
    java.lang.Float area, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> flags, @org.jetbrains.annotations.Nullable
    java.util.List<com.example.domain.dto.LanguageDto> languages, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.Double> latlng, @org.jetbrains.annotations.Nullable
    java.lang.Integer population) {
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
    
    public CountryItem(@org.jetbrains.annotations.Nullable
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.String capital, @org.jetbrains.annotations.Nullable
    java.lang.Float area, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> flags, @org.jetbrains.annotations.Nullable
    java.util.List<com.example.domain.dto.LanguageDto> languages, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.Double> latlng, @org.jetbrains.annotations.Nullable
    java.lang.Integer population) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCapital() {
        return null;
    }
    
    public final void setCapital(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Float component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Float getArea() {
        return null;
    }
    
    public final void setArea(@org.jetbrains.annotations.Nullable
    java.lang.Float p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> getFlags() {
        return null;
    }
    
    public final void setFlags(@org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.example.domain.dto.LanguageDto> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.example.domain.dto.LanguageDto> getLanguages() {
        return null;
    }
    
    public final void setLanguages(@org.jetbrains.annotations.Nullable
    java.util.List<com.example.domain.dto.LanguageDto> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.Double> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.Double> getLatlng() {
        return null;
    }
    
    public final void setLatlng(@org.jetbrains.annotations.Nullable
    java.util.List<java.lang.Double> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getPopulation() {
        return null;
    }
    
    public final void setPopulation(@org.jetbrains.annotations.Nullable
    java.lang.Integer p0) {
    }
}