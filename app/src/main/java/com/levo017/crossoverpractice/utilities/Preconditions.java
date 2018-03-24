package com.levo017.crossoverpractice.utilities;

/**
 * Created by dyan017 on 1/7/2018.
 */

public class Preconditions {
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
