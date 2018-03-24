package com.levo017.crossoverpractice.features.login.iterators;

import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.ThreadExecutor;
import com.levo017.crossoverpractice.iteractors.CompletableUseCase;
import com.levo017.crossoverpractice.repositories.ApplicationRepository;

import javax.inject.Inject;
import io.reactivex.Completable;

/**
 * Created by dyan017 on 3/10/2018.
 */

public class LoginUseCase extends CompletableUseCase<String> {
    ApplicationRepository repository;

    @Inject
    public LoginUseCase(ApplicationRepository repository, ThreadExecutor executor, PostExecutionThread postThread) {
        super(executor, postThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildCompletableUseCase(String userID) {
        return Completable.fromSingle(repository.loadUsers(userID));
    }
}
