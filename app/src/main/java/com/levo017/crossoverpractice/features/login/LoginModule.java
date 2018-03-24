package com.levo017.crossoverpractice.features.login;


import com.levo017.crossoverpractice.executors.SchedulersFacade;
import com.levo017.crossoverpractice.features.login.iterators.LoginUseCase;
import com.levo017.crossoverpractice.features.login.views.LoginActivity;
import com.levo017.crossoverpractice.features.login.views.LoginView;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by dyan017 on 3/10/2018.
 */
@Module
public abstract class LoginModule {
    @Binds
    public abstract LoginView provideAddUserView(LoginActivity loginActivity);

    @Provides
    static LoginViewModelFactory provideLoginViewModelFactory(LoginUseCase loginUseCase,
                                                       SchedulersFacade schedulersFacade) {
        return new LoginViewModelFactory(loginUseCase, schedulersFacade);
    }
}
