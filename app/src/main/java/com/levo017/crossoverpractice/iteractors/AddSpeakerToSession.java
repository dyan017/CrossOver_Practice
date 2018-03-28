package com.levo017.crossoverpractice.iteractors;

import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.ThreadExecutor;
import com.levo017.crossoverpractice.repositories.ApplicationRepository;

import io.reactivex.Completable;

/**
 * Created by dyan017 on 1/11/2018.
 */

public class AddSpeakerToSession extends CompletableUseCase<AddSpeakerToSession.Param> {
    ApplicationRepository repository;

    AddSpeakerToSession(ThreadExecutor executor, PostExecutionThread postThread, ApplicationRepository repository) {
        super(executor, postThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildCompletableUseCase(Param param) {
        //retrieve the Session object
        return repository.addSpeakerToSession(param.UserId, param.SessionId);
    }

    public class Param {
        Integer SessionId;
        Integer UserId;
    }
}
