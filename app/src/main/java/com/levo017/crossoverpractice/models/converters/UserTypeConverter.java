package com.levo017.crossoverpractice.models.converters;

import android.arch.persistence.room.TypeConverter;

import com.levo017.crossoverpractice.models.UserType;

/**
 * Created by dyan017 on 3/23/2018.
 */

public class UserTypeConverter {
    @TypeConverter
    public static String toString(UserType userType){
        return userType.toString();
    }

    @TypeConverter
    public static UserType toUserType(String string){
        return Enum.valueOf(UserType.class, string);
    }
}
