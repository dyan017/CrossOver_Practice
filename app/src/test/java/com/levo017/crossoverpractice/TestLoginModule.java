package com.levo017.crossoverpractice;

import com.levo017.crossoverpractice.executors.SchedulersFacade;
import com.levo017.crossoverpractice.features.login.LoginModule;
import com.levo017.crossoverpractice.features.login.LoginViewModelFactory;
import com.levo017.crossoverpractice.features.login.iterators.LoginUseCase;
import com.levo017.crossoverpractice.features.login.views.LoginActivity;
import com.levo017.crossoverpractice.features.login.views.LoginView;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by dyan017 on 4/22/2018.
 */
@Module
public abstract class TestLoginModule extends LoginModule {
 /*   @Binds
    public abstract LoginViewModelFactory provideLoginViewModelFactory(LoginViewModelFactory factory);*/
}
