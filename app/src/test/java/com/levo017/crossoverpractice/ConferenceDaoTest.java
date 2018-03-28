package com.levo017.crossoverpractice;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.models.Session;
import com.levo017.crossoverpractice.models.Topic;
import com.levo017.crossoverpractice.persistence.AppDatabase;
import com.levo017.crossoverpractice.persistence.dao.ConferenceDao;
import com.levo017.crossoverpractice.persistence.dao.SessionDao;
import com.levo017.crossoverpractice.persistence.dao.TopicDao;
import com.levo017.crossoverpractice.persistence.relationships.ConferenceTopicRelationship;
import com.levo017.crossoverpractice.persistence.relationships.SessionsInConference;

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
import static junit.framework.Assert.assertTrue;

/**
 * Created by dyan017 on 3/25/2018.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ConferenceDaoTest {
    AppDatabase database;
    ConferenceDao conferenceDao;
    SessionDao sessionDao;
    TopicDao topicDao;

    @Before
    public void setUp() {
        Context context = RuntimeEnvironment.application;
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build();
        conferenceDao = database.conferenceDao();
        sessionDao = database.sessionDao();
        topicDao = database.topicDao();
    }

    @After
    public void tearDown(){
        database.close();
        database = null;
        conferenceDao = null;
        sessionDao = null;
        topicDao = null;
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
        Topic testTopic = new Topic();
        long topicId = topicDao.insertTopic(testTopic);

        conferenceDao.addTopic(new ConferenceTopicRelationship((int)conferenceId, (int)topicId));
        TestSubscriber<Conference> testSubscriber = conferenceDao.queryConferenceForTopic((int)topicId).test();
        testSubscriber.awaitDone(5, TimeUnit.SECONDS);
        List<Conference> result = testSubscriber.values();

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).ConferenceId, conferenceId);
    }

    @Test //M-to-N relationship.
    public void updateTopicToConference(){
        Conference testConference = new Conference();
        long conferenceId = conferenceDao.insertConference(testConference);
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

    @Test //One-to-Many relationship
    public void queryConferenceWithSession(){
        Conference testConference = new Conference();
        long conferenceId = conferenceDao.insertConference(testConference);

        Session session = new Session();
        long sessionId = sessionDao.insertSession(session);

        TestSubscriber<SessionsInConference> testSubscriber = conferenceDao.queryConferenceWithSessions().test();
        testSubscriber.awaitDone(5, TimeUnit.SECONDS);
        List<SessionsInConference> result = testSubscriber.values();

        assertEquals(result.size(), 1);

        session.Note = "This is a new Session2";
        session.Id = (int)sessionId;
        sessionDao.updateSession(session);

        TestSubscriber<Session> testSessionSubscriber = sessionDao.querySessions().test();
        testSessionSubscriber.awaitDone(2, TimeUnit.SECONDS);
        assertEquals(testSessionSubscriber.values().size(), 1);

        testSubscriber = conferenceDao.queryConferenceWithSessions().test();
        testSubscriber.awaitDone(5, TimeUnit.SECONDS);
        result = testSubscriber.values();

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).sessions.get(0).Note , "This is a new Session2");
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
