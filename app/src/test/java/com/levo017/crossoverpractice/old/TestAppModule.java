package com.levo017.crossoverpractice.old;

import com.levo017.crossoverpractice.persistence.AppDatabase;
import com.levo017.crossoverpractice.persistence.dao.ConferenceDao;
import com.levo017.crossoverpractice.persistence.dao.InviteDao;
import com.levo017.crossoverpractice.persistence.dao.SessionDao;
import com.levo017.crossoverpractice.persistence.dao.TopicDao;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dyan017 on 3/9/2018.
 */
/*@Module
public class TestAppModule {
    @Singleton
    @Provides
    AppDatabase providesAppDatabase(){
        AppDatabase database = Mockito.mock(AppDatabase.class);

        ConferenceDao mockConferenceDao = Mockito.mock(ConferenceDao.class);
        Mockito.when(database.conferenceDao()).thenReturn(mockConferenceDao);

        InviteDao mockInviteDao = Mockito.mock(InviteDao.class);
        Mockito.when(database.inviteDao()).thenReturn(mockInviteDao);

        SessionDao sessionDao = Mockito.mock(SessionDao.class);
        Mockito.when(database.sessionDao()).thenReturn(sessionDao);

        TopicDao topicDao = Mockito.mock(TopicDao.class);
        Mockito.when(database.topicDao()).thenReturn(topicDao);

        return database;
    }
}*/
