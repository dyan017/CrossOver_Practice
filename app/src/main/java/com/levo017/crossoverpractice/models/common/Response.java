package com.levo017.crossoverpractice.models.common;

import android.support.annotation.Nullable;

import static com.levo017.crossoverpractice.models.common.Status.ERROR;
import static com.levo017.crossoverpractice.models.common.Status.SUCCESS;

/**
 * Created by dyan017 on 4/6/2018.
 */

public class Response<T> {
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable error;

    private Response(Status status, @Nullable T data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(SUCCESS, data, null);
    }

    public static <T> Response<T> error(Throwable error) {
        return new Response<>(ERROR, null, error);
    }
}
