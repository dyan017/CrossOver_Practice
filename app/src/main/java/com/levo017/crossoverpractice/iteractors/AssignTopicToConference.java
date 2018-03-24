package com.levo017.crossoverpractice.iteractors;

import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.ThreadExecutor;
import com.levo017.crossoverpractice.persistence.relationships.ConferenceTopicRelationship;
import com.levo017.crossoverpractice.repositories.ApplicationRepository;

import io.reactivex.Completable;

/**
 * Created by dyan017 on 1/11/2018.
 */

public class AssignTopicToConference extends CompletableUseCase<AssignTopicToConference.Param> {

    ApplicationRepository repository;

    AssignTopicToConference(ThreadExecutor executor, PostExecutionThread postThread, ApplicationRepository repository) {
        super(executor, postThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildCompletableUseCase(AssignTopicToConference.Param param) {
        return repository.addTopicToConference(param.ConferenceId, param.TopicId);
    }

    public class Param {
        String TopicId;
        String ConferenceId;
    }
}
