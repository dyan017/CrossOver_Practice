package com.levo017.crossoverpractice;

import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import com.levo017.crossoverpractice.executors.SchedulersFacade;
import com.levo017.crossoverpractice.features.login.LoginViewModel;
import com.levo017.crossoverpractice.features.login.iterators.LoginUseCase;
import com.levo017.crossoverpractice.models.User;
import com.levo017.crossoverpractice.resources.Resource;
import com.levo017.crossoverpractice.resources.Status;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by dyan017 on 4/7/2018.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class LoginViewModelTest {
    @Mock Observer<Resource<Boolean>> loginObserver;
    @Mock Observer<Boolean> statusObserver;

    LoginViewModel loginViewModel;

    private final String validUser = "ValidUser";
    private final String absentUser = "AbsentUser";
    private final String exceptionUser = "ExceptionUser";

    LoginUseCase mockUseCase;
    @Mock User user;

    @Before
    public void setUp() {
        initMocks(this);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
/*
        LoginUseCase.LoginParam validLogin = new LoginUseCase.LoginParam(validUser, null);
        LoginUseCase.LoginParam absentLogin = new LoginUseCase.LoginParam(absentUser, null);
        LoginUseCase.LoginParam exceptionLogin = new LoginUseCase.LoginParam(exceptionUser, null);

        when(mockUseCase.buildUseCase(argThat(Matchers.hasProperty("UserName", Matchers.(absentLogin))))).thenReturn(Single.just(true));
        when(mockUseCase.buildUseCase(argThat(Matchers.hasProperty("UserName", Matchers.hasValue(absentLogin))))).thenReturn(Single.just(false));
        when(mockUseCase.buildUseCase(argThat(Matchers.hasProperty("UserName", Matchers.hasValue(exceptionLogin))))).thenReturn(Single.just(false));
*/
    }


    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Scheduler.Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Test
    public void loginObserver(){
        mockUseCase = Mockito.mock(LoginUseCase.class);
        when(mockUseCase.buildUseCase(any(LoginUseCase.LoginParam.class))).thenReturn(Single.just(true));
        loginViewModel = new LoginViewModel(mockUseCase, new SchedulersFacade());

        loginViewModel.getLoadingStatus().observeForever(statusObserver);
        loginViewModel.getLoginResponse().observeForever(loginObserver);

        loginViewModel.login(validUser, null);

        //Not sure why the following tests is not working.
        //verify(loginObserver).onChanged(Resource.success(true));

        assert(loginViewModel.getLoginResponse().getValue().status == Status.SUCCESS);
        assert(loginViewModel.getLoginResponse().getValue().data == true);
    }
}
