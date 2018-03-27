package com.levo017.crossoverpractice.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.models.Session;
import com.levo017.crossoverpractice.models.Topic;
import com.levo017.crossoverpractice.persistence.relationships.ConferenceTopicRelationship;

import java.util.List;

import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by dyan017 on 1/11/2018.
 */
@Dao
public interface TopicDao {
    @Insert(onConflict = REPLACE)
    long insertTopic(Topic topic);

    @Delete
    void deleteTopic(Topic topic);

    @Update
    void updateTopic(Topic topic);

    @Query("SELECT Topic.* FROM Topic")
    Flowable<Topic> queryTopic();

    @Query("SELECT Topic.* FROM Topic\n" +
            "INNER JOIN conference_topic_join ON \n" +
            "conference_topic_join.topicId = Topic.topicId \n" +
            "WHERE conference_topic_join.topicId=:topicId")
    Flowable<Topic> queryTopicsForConference(int topicId);

    @Insert(onConflict = REPLACE)
    void addConference(ConferenceTopicRelationship relationship);

}
