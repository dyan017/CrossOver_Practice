package com.levo017.crossoverpractice;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.models.Topic;
import com.levo017.crossoverpractice.persistence.AppDatabase;
import com.levo017.crossoverpractice.persistence.dao.ConferenceDao;
import com.levo017.crossoverpractice.persistence.dao.SessionDao;
import com.levo017.crossoverpractice.persistence.dao.TopicDao;
import com.levo017.crossoverpractice.persistence.relationships.ConferenceTopicRelationship;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.subscribers.TestSubscriber;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dyan017 on 3/25/2018.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ConferenceDaoTest {
    AppDatabase database;
    ConferenceDao conferenceDao;

    @Before
    public void setUp() {
        Context context = RuntimeEnvironment.application;
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build();
        conferenceDao = database.conferenceDao();
    }

    @After
    public void tearDown(){
        database.close();
        database = null;
        conferenceDao = null;
    }

    @Test
    public void insertConference() throws InterruptedException {
        TestSubscriber<Conference> subscriber =conferenceDao.queryConference().test();
        subscriber.awaitDone(2, TimeUnit.SECONDS);
        //subscriber.awaitTerminalEvent();

        List<Conference> conferences = subscriber.values();
        assertEquals(conferences.size(), 0);
        Conference testConference = new Conference();
        long newId = conferenceDao.insertConference(testConference);
        subscriber =conferenceDao.queryConference().test();
        subscriber.awaitDone(2, TimeUnit.SECONDS);

        conferences = subscriber.values();
        assertEquals(conferences.size(), 1);
        assertEquals(newId, conferences.get(0).ConferenceId);   //Confirm long returned by insert is the new ID.
    }

    @Test
    public void insertTopicToConference(){
        Conference testConference = new Conference();
        long conferenceId = conferenceDao.insertConference(testConference);
        TopicDao topicDao = database.topicDao();
        Topic testTopic = new Topic();
        long topicId = topicDao.insertTopic(testTopic);

        conferenceDao.addTopic(new ConferenceTopicRelationship((int)conferenceId, (int)topicId));
        TestSubscriber<Conference> testSubscriber = conferenceDao.queryConferenceForTopic((int)topicId).test();
        testSubscriber.awaitDone(5, TimeUnit.SECONDS);
        List<Conference> result = testSubscriber.values();

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).ConferenceId, conferenceId);
    }

    @Test
    public void updateTopicToConference(){
        Conference testConference = new Conference();
        long conferenceId = conferenceDao.insertConference(testConference);
        TopicDao topicDao = database.topicDao();
        Topic testTopic = new Topic();
        long topicId = topicDao.insertTopic(testTopic);

        conferenceDao.addTopic(new ConferenceTopicRelationship((int)conferenceId, (int)topicId));
        TestSubscriber<Conference> testSubscriber = conferenceDao.queryConferenceForTopic((int)topicId).test();
        testSubscriber.awaitDone(5, TimeUnit.SECONDS);
        List<Conference> result = testSubscriber.values();

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).ConferenceId, conferenceId);

        conferenceDao.removeTopic(new ConferenceTopicRelationship((int)conferenceId, (int)topicId));
        testSubscriber = conferenceDao.queryConferenceForTopic((int)topicId).test();
        testSubscriber.awaitDone(5, TimeUnit.SECONDS);
        result = testSubscriber.values();

        assertEquals(result.size(), 0);
    }

    @Test
    public void updateConference(){

    }

    @Test
    public void deleteConference(){

    }

    @Test
    public void queryTopicsForConference(){

    }

    @Test
    public void addTopicsForConference(){

    }

    @Test
    public void removeTopicForConference(){

    }
}
