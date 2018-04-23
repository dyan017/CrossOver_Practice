package com.levo017.crossoverpractice.iterators;

import android.support.annotation.NonNull;

import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.ThreadExecutor;
import com.levo017.crossoverpractice.features.login.iterators.LoginUseCase;
import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.models.User;
import com.levo017.crossoverpractice.repositories.ApplicationRepository;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subscribers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.mockito.Mockito.when;


/**
 * Created by dyan017 on 4/3/2018.
 */

public class LoginUseCaseTest {

    private final String validUser = "ValidUser";
    private final String absentUser = "AbsentUser";
    private final String exceptionUser = "ExceptionUser";

    @Mock
    ThreadExecutor threadExecutor;
    @Mock PostExecutionThread postExecutionThread;

    LoginUseCase login;

    ApplicationRepository mockRepos;

    @Before
    public void setUp() {
        mockRepos = Mockito.mock(ApplicationRepository.class);
        User user = new User();
        user.Password = "Fake Password";
        when(mockRepos.findUsersByUserName(validUser)).thenReturn(Maybe.just(user));
        when(mockRepos.findUsersByUserName(absentUser)).thenReturn(Maybe.empty());
        when(mockRepos.findUsersByUserName(exceptionUser)).thenReturn(Maybe.error(new Exception()));

        login = new LoginUseCase(mockRepos, threadExecutor, postExecutionThread);
    }

    @Test
    public void testExistingUser(){
        LoginUseCase.LoginParam param = new LoginUseCase.LoginParam(validUser, "");
        TestObserver<Boolean> observer = login.buildUseCase(param).test();
        observer.assertComplete();
    }

    @Test
    public void testExceptionUser(){
        LoginUseCase.LoginParam param = new LoginUseCase.LoginParam(exceptionUser, "");
        TestObserver<Boolean> observer = login.buildUseCase(param).test();
        assertEquals(observer.errorCount(), 1);
    }

    @Test
    public void testAbsentUser(){
        LoginUseCase.LoginParam param = new LoginUseCase.LoginParam(absentUser, "");
        TestObserver<Boolean> observer = login.buildUseCase(param).test();
        assertFalse(observer.values().get(0));
        observer.assertNoErrors();
        observer.assertComplete();
    }
}
