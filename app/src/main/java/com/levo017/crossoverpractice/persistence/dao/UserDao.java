package com.levo017.crossoverpractice.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.levo017.crossoverpractice.models.Session;
import com.levo017.crossoverpractice.models.User;

/**
 * Created by dyan017 on 1/11/2018.
 */
@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Delete
    void deleteSession(User user);

    @Update
    void updateSession(User user);
}
