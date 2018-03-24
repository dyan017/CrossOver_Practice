package com.levo017.crossoverpractice.resources;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.regex.Pattern;

import retrofit2.Response;

/**
 * Created by dyan017 on 1/7/2018.
 */

public class ApiResponse<T> {
    private static final Pattern LINK_PATTERN = Pattern
            .compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"");
    private static final Pattern PAGE_PATTERN = Pattern.compile("page=(\\d)+");
    private static final String NEXT_LINK = "next";

    public final int code;
    @Nullable
    public final T body;
    @Nullable
    public final String errorMessage;

    public ApiResponse(Throwable error) {
        code = 500;
        body = null;
        errorMessage = error.getMessage();
    }

    public ApiResponse(Response<T> response){
        code = response.code();
        if (response.isSuccessful()) {
            body = response.body();
            errorMessage = null;
        } else {
            String message = null;
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().string();
                } catch (IOException ignored) {
                    //Timber.e(ignored, "error while parsing response");
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            errorMessage = message;
            body = null;
        }
        //todo: don't like the way this handling is structured.  It's adding specific request handling inside a generic request.
        //the formating in OctopusResourcesResponse should be minimal, and post to an EventBus,
        //specific API Handler can listen to the EventBus for events.
    }

    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }
}
