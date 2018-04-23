package com.levo017.crossoverpractice.features.login.iterators;

import android.content.OperationApplicationException;
import android.util.Log;

import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.ThreadExecutor;
import com.levo017.crossoverpractice.iteractors.CompletableUseCase;
import com.levo017.crossoverpractice.iteractors.ObservableUseCase;
import com.levo017.crossoverpractice.iteractors.SingleUseCase;
import com.levo017.crossoverpractice.iteractors.exceptions.UserNotFoundException;
import com.levo017.crossoverpractice.models.User;
import com.levo017.crossoverpractice.repositories.ApplicationRepository;

import java.util.NoSuchElementException;

import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by dyan017 on 3/10/2018.
 */

public class LoginUseCase extends SingleUseCase<Boolean, LoginUseCase.LoginParam> {
    ApplicationRepository repository;
    CompositeDisposable disposables;

    final String Tag = "LoginUseCase";

    @Inject
    public LoginUseCase(ApplicationRepository repository, ThreadExecutor executor, PostExecutionThread postThread) {
        super(executor, postThread);
        this.repository = repository;
        disposables = new CompositeDisposable();
    }

    @Override
    public Single<Boolean> buildUseCase(LoginParam loginParam) {
        String userName = loginParam.userName;
        String password = loginParam.password;

        return repository.findUsersByUserName(userName).flatMapSingle(value -> {
                if (value == null){
                    return Single.just(false);
                }else if (!(value.Password.equalsIgnoreCase(password))){
                    return Single.just(false);
                }
                return Single.just(true);
         }).onErrorResumeNext(value->{
                return Single.just(false);
         });
    }

    public static class LoginParam {
        public LoginParam(String userName, String password){
            this.userName = userName;
            this.password = password;
        }
        public String userName;
        public String password;

        public String getUserName() {
            return userName;
        }
    }
}
