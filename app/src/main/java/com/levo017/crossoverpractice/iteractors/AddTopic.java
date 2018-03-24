package com.levo017.crossoverpractice.iteractors;

import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.ThreadExecutor;
import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.repositories.ApplicationRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by dyan017 on 1/11/2018.
 */

public class AddTopic extends CompletableUseCase<Conference> {

    ApplicationRepository repository;

    @Inject
    AddTopic(ThreadExecutor executor, PostExecutionThread postThread, ApplicationRepository repository) {
        super(executor, postThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildCompletableUseCase(Conference conference) {
        return repository.addConference(conference);
    }

}
