package com.levo017.crossoverpractice.features.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.levo017.crossoverpractice.executors.SchedulersFacade;
import com.levo017.crossoverpractice.features.login.iterators.LoginUseCase;

/**
 * Created by dyan017 on 3/12/2018.
 */

public class LoginViewModel extends ViewModel {

    private final SchedulersFacade schedulersFacade;
    private final LoginUseCase loginUseCase;
    private final MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();

    LoginViewModel(LoginUseCase loginUseCase,
                   SchedulersFacade schedulersFacade) {
        this.loginUseCase = loginUseCase;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    protected void onCleared(){}

    void login(){
        //loginUseCase.execute();
    }
}
