package com.levo017.crossoverpractice.repositories;


import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.models.Invite;
import com.levo017.crossoverpractice.models.Session;
import com.levo017.crossoverpractice.models.Topic;
import com.levo017.crossoverpractice.models.User;
import com.levo017.crossoverpractice.persistence.relationships.ConferenceTopicRelationship;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by dyan017 on 1/9/2018.
 */

public interface ApplicationRepository {
    public Completable addConference(Conference conference);
    public Completable addTopic(Topic topic);
    public Completable addSession(Session session);
    public Completable addInvite(Invite invite);

    Completable addTopicToConference(int topicId, int conferenceId);
    Completable addSpeakerToSession(String userId, String sessionId);

    Single<Conference> loadConferences(String conferenceId);
    Single<Session> loadSessions(String sessionId);
    Single<User> loadUsers(String userId);
    Single<Topic> loadTopics(String topicId);
}
