package com.levo017.crossoverpractice.repositories;

import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.models.Invite;
import com.levo017.crossoverpractice.models.Session;
import com.levo017.crossoverpractice.models.Topic;
import com.levo017.crossoverpractice.models.User;
import com.levo017.crossoverpractice.persistence.AppDatabase;
import com.levo017.crossoverpractice.persistence.dao.ConferenceDao;
import com.levo017.crossoverpractice.persistence.dao.InviteDao;
import com.levo017.crossoverpractice.persistence.dao.SessionDao;
import com.levo017.crossoverpractice.persistence.dao.TopicDao;
import com.levo017.crossoverpractice.persistence.dao.UserDao;
import com.levo017.crossoverpractice.persistence.relationships.ConferenceTopicRelationship;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by dyan017 on 3/14/2018.
 */

public class ApplicationRepositoryImpl implements ApplicationRepository {
    ConferenceDao conferenceDao;
    InviteDao inviteDao;
    SessionDao sessionDao;
    TopicDao topicDao;
    UserDao userDao;

    @Inject
    public ApplicationRepositoryImpl(AppDatabase appDatabase){
        conferenceDao = appDatabase.conferenceDao();
        inviteDao = appDatabase.inviteDao();
        sessionDao = appDatabase.sessionDao();
        topicDao = appDatabase.topicDao();
        userDao = appDatabase.userDao();
    }

    @Override
    public Completable addConference(Conference conference) {
        return Completable.fromAction(()-> conferenceDao.insertConference(conference));
    }

    @Override
    public Completable addTopic(Topic topic) {
        return Completable.fromAction(()-> topicDao.insertTopic(topic));
    }

    @Override
    public Completable addSession(Session session) {
        return Completable.fromAction(()-> sessionDao.insertSession(session));
    }

    @Override
    public Completable addInvite(Invite invite) {
        return Completable.fromAction(()-> inviteDao.InsertInvite(invite));
    }

    @Override
    public Completable addTopicToConference(String conferenceId, String topicId) {
        ConferenceTopicRelationship relationship = new ConferenceTopicRelationship(conferenceId, topicId);
        return Completable.fromAction(()->conferenceDao.addTopic(relationship));
    }

    @Override
    public Completable addSpeakerToSession(String userId, String sessionId) {
        return Completable.fromAction(()->sessionDao.AssignUserToSession(userId, sessionId));
    }

    @Override
    public Single<Conference> loadConferences(String conferenceId) {
        return null;
    }

    @Override
    public Single<Session> loadSessions(String sessionId) {
        return null;
    }

    @Override
    public Single<User> loadUsers(String userId) {
        return null;
    }

    @Override
    public Single<Topic> loadTopics(String topicId) {
        return null;
    }
}
