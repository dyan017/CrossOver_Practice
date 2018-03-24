package com.levo017.crossoverpractice.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.levo017.crossoverpractice.models.Session;
import com.levo017.crossoverpractice.models.Topic;

/**
 * Created by dyan017 on 1/11/2018.
 */
@Dao
public interface TopicDao {
    @Insert
    void insertTopic(Topic topic);

    @Delete
    void deleteTopic(Topic topic);

    @Update
    void updateTopic(Topic topic);
}
