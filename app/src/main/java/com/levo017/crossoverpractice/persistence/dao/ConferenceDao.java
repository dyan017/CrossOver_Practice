package com.levo017.crossoverpractice.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.models.Session;
import com.levo017.crossoverpractice.persistence.relationships.SessionsInConference;
import com.levo017.crossoverpractice.persistence.relationships.ConferenceTopicRelationship;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by dyan017 on 1/10/2018.
 */
@Dao
public interface ConferenceDao {
    @Insert(onConflict = REPLACE)
    Long insertConference(Conference conference);

    @Insert(onConflict = REPLACE)
    void addTopic(ConferenceTopicRelationship relationship);

    @Insert(onConflict = REPLACE)
    void addSession(Session session);

    @Query("SELECT Conference.* FROM Conference")
    Flowable<Conference> queryConference();

    @Query("SELECT Conference.* FROM Conference\n" +
            "WHERE Conference.ConferenceId=:conferenceId")
    Maybe<Conference> queryConference(int conferenceId);

    @Query("SELECT Conference.* FROM Conference\n" +
            "INNER JOIN conference_topic_join ON \n" +
            "conference_topic_join.conferenceId = Conference.conferenceId \n" +
            "WHERE conference_topic_join.topicId=:topicId")
    Flowable<Conference> queryConferenceForTopic(int topicId);

    @Query("SELECT * FROM Conference")
    Flowable<SessionsInConference> queryConferenceWithSessions();

    @Query("SELECT * FROM Conference Where ConferenceId=:conferenceId")
    Maybe<SessionsInConference> queryConferenceWithSessions(int conferenceId);

    @Delete
    void removeTopic(ConferenceTopicRelationship mapping);

    @Delete
    void deleteConference(Conference conference);

    @Update
    void updateConference(Conference conference);
}
