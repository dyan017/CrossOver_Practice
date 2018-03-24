package com.levo017.crossoverpractice.resources;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.levo017.crossoverpractice.resources.Status.ERROR;
import static com.levo017.crossoverpractice.resources.Status.LOADING;
import static com.levo017.crossoverpractice.resources.Status.SUCCESS;

/**
 * Created by dyan017 on 1/7/2018.
 */

public class Resource<T> {
    @NonNull
    public final Status status;

    @Nullable
    public final String message;

    @Nullable
    public  T data;

    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }
}
