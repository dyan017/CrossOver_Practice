
package com.levo017.crossoverpractice;

import com.levo017.crossoverpractice.injection.ApplicationComponent;
import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.old.JUnitDaggerMockRule;
import com.levo017.crossoverpractice.persistence.AppDatabase;
import com.levo017.crossoverpractice.persistence.dao.ConferenceDao;
import com.levo017.crossoverpractice.persistence.dao.InviteDao;
import com.levo017.crossoverpractice.persistence.dao.SessionDao;
import com.levo017.crossoverpractice.persistence.dao.TopicDao;
import com.levo017.crossoverpractice.repositories.ApplicationRepository;
import com.levo017.crossoverpractice.repositories.ApplicationRepositoryImpl;
import com.levo017.crossoverpractice.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import it.cosenonjaviste.daggermock.InjectFromComponent;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * Created by dyan017 on 3/14/2018.
 */

public class ApplicationRepositoryImplTest {
    @Rule public JUnitDaggerMockRule rule = new JUnitDaggerMockRule();

    @Mock
    AppDatabase mockDB;

    ConferenceDao mockConferenceDao;
    InviteDao mockInviteDao;
    SessionDao mockSessionDao;
    TopicDao mockTopicDao;

    @InjectFromComponent(MainActivity.class)
    ApplicationRepositoryImpl applicationRepository;

    @Test
    public void repositoryAddConference(){
        initialiseMockDB();
        Conference conference = new Conference();
        applicationRepository.addConference(conference);
        verify(mockConferenceDao, times(1)).insertConference(conference);
    }

    void initialiseMockDB(){
        mockDB = Mockito.mock(AppDatabase.class);

        mockConferenceDao = Mockito.mock(ConferenceDao.class);
        Mockito.when(mockDB.conferenceDao()).thenReturn(mockConferenceDao);

        mockInviteDao = Mockito.mock(InviteDao.class);
        Mockito.when(mockDB.inviteDao()).thenReturn(mockInviteDao);

        mockSessionDao = Mockito.mock(SessionDao.class);
        Mockito.when(mockDB.sessionDao()).thenReturn(mockSessionDao);

        mockTopicDao = Mockito.mock(TopicDao.class);
        Mockito.when(mockDB.topicDao()).thenReturn(mockTopicDao);
    }
}
