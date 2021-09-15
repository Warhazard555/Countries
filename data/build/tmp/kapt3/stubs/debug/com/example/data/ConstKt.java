package com.example.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u001a\u0010\u0005\u001a\u00020\u0001X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\"\u000e\u0010\n\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\rX\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0012X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0017X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0017X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0017X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"API_ALL", "", "BASE_URL", "CAPITAL_URL", "COUNTRY_FILTER_LISTNER_KEY", "COUNTRY_FIND_NAME", "getCOUNTRY_FIND_NAME", "()Ljava/lang/String;", "setCOUNTRY_FIND_NAME", "(Ljava/lang/String;)V", "COUNTRY_FLAG_KEY", "COUNTRY_NAME_KEY", "DEBOUNCE_TIME_MILLIS", "", "ERROR", "FILE_NAME_SHARED_PREF", "FILTER_COUNTRY_KEY", "FLOAT_ZERO", "", "GET_CAPITAL_BY_NAME", "GET_COUNTRY_BY_NAME", "KEY_SORT", "MIN_SEARCH_STRING_LENGTH", "", "PATH_URL", "PATH_VARIABLE", "THOUSAND", "ZERO", "data_debug"})
public final class ConstKt {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String FILE_NAME_SHARED_PREF = "data";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_SORT = "SortStatus";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String BASE_URL = "https://restcountries.eu/rest/v2/";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CAPITAL_URL = "all?fields=capital";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String API_ALL = "all";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String COUNTRY_NAME_KEY = "COUNTRY_NAME_KEY";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String COUNTRY_FLAG_KEY = "COUNTRY_FLAG_KEY";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ERROR = "ERROR";
    @org.jetbrains.annotations.NotNull
    private static java.lang.String COUNTRY_FIND_NAME = "COUNTRY_FIND_NAME";
    public static final int MIN_SEARCH_STRING_LENGTH = 3;
    public static final long DEBOUNCE_TIME_MILLIS = 300L;
    public static final int ZERO = 0;
    public static final int THOUSAND = 1000;
    public static final float FLOAT_ZERO = 0.0F;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PATH_VARIABLE = "path";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PATH_URL = "/{path}";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String GET_COUNTRY_BY_NAME = "name/{path}";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String GET_CAPITAL_BY_NAME = "capital/{path}";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String FILTER_COUNTRY_KEY = "FILTER_COUNTRY_KEY";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String COUNTRY_FILTER_LISTNER_KEY = "COUNTRY_FILTER_LISTNER_KEY";
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String getCOUNTRY_FIND_NAME() {
        return null;
    }
    
    public static final void setCOUNTRY_FIND_NAME(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
}