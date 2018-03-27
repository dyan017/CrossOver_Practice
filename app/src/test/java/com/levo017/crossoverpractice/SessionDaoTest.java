package com.levo017.crossoverpractice;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.levo017.crossoverpractice.injection.ApplicationComponent;
import com.levo017.crossoverpractice.persistence.AppDatabase;
import com.levo017.crossoverpractice.persistence.dao.SessionDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Created by dyan017 on 3/25/2018.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SessionDaoTest {
    AppDatabase database;
    SessionDao sessionDao;

    @Before
    void setUp() {
        Context context = RuntimeEnvironment.application;
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        sessionDao = database.sessionDao();
    }
}
