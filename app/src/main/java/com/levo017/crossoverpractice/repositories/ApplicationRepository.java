package com.levo017.crossoverpractice.repositories;


import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.models.Invite;
import com.levo017.crossoverpractice.models.Session;
import com.levo017.crossoverpractice.models.Topic;
import com.levo017.crossoverpractice.models.User;
import com.levo017.crossoverpractice.persistence.relationships.ConferenceTopicRelationship;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by dyan017 on 1/9/2018.
 */

public interface ApplicationRepository {
    Completable addConference(Conference conference);
    Completable addTopic(Topic topic);
    Completable addSession(Session session);
    Completable addInvite(Invite invite);

    Completable addTopicToConference(int topicId, int conferenceId);
    Completable addSpeakerToSession(int userId, int sessionId);

    Single<Conference> loadConferences(String conferenceId);
    Single<Session> loadSessions(String sessionId);
    Single<User> loadUsers(String userId);
    Maybe<User> findUsersByUserName(String userName);
    Single<Topic> loadTopics(String topicId);
}
