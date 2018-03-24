package com.levo017.crossoverpractice.features.login;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.levo017.crossoverpractice.executors.SchedulersFacade;
import com.levo017.crossoverpractice.features.login.iterators.LoginUseCase;


/**
 * Created by dyan017 on 3/12/2018.
 */

public class LoginViewModelFactory implements ViewModelProvider.Factory  {

    private final LoginUseCase loginUseCase;
    private final SchedulersFacade schedulersFacade;

    LoginViewModelFactory(LoginUseCase loginUseCase, SchedulersFacade schedulersFacade){
        this.loginUseCase = loginUseCase;
        this.schedulersFacade = schedulersFacade;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(loginUseCase, schedulersFacade);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
