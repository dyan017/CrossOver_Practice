package com.levo017.crossoverpractice;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.levo017.crossoverpractice.models.Topic;
import com.levo017.crossoverpractice.persistence.AppDatabase;
import com.levo017.crossoverpractice.persistence.dao.TopicDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.subscribers.TestSubscriber;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dyan017 on 3/27/2018.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TopicDaoTest {
    AppDatabase database;
    TopicDao topicDao;

    @Before
    public void setUp() {
        Context context = RuntimeEnvironment.application;
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build();
        topicDao = database.topicDao();
    }

    @After
    public void tearDown(){
        database.close();
        database = null;
        topicDao = null;
    }

    @Test
    public void insertTopic() throws InterruptedException {

        Topic testTopic = new Topic();
        long newId = topicDao.insertTopic(testTopic);

        TestSubscriber<Topic> subscriber = topicDao.queryTopic().test();
        subscriber.await(3, TimeUnit.SECONDS);

        List<Topic> topics = subscriber.values();
        assertEquals(1, topics.size());
        assertEquals(newId, topics.get(0).TopicId);   //Confirm long returned by insert is the new ID.
    }
}
