package com.levo017.crossoverpractice.features.users.iterators;

import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.ThreadExecutor;
import com.levo017.crossoverpractice.iteractors.CompletableUseCase;
import com.levo017.crossoverpractice.repositories.ApplicationRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by dyan017 on 3/3/2018.
 */

public class SaveUser extends CompletableUseCase {

    ApplicationRepository applicationRepository;

    @Inject
    SaveUser(ApplicationRepository repository, ThreadExecutor threadExecutor, PostExecutionThread thread){
        super(threadExecutor, thread);
        applicationRepository = repository;
    }

    @Override
    protected Completable buildCompletableUseCase(Object o) {
        return null;
    }
}
