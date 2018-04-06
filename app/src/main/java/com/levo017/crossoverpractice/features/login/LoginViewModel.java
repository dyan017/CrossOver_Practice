package com.levo017.crossoverpractice.features.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.levo017.crossoverpractice.executors.SchedulersFacade;
import com.levo017.crossoverpractice.features.login.iterators.LoginUseCase;
import com.levo017.crossoverpractice.models.common.Response;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by dyan017 on 3/12/2018.
 */

public class LoginViewModel extends ViewModel {

    private final SchedulersFacade schedulersFacade;
    private final LoginUseCase loginUseCase;
    private final MutableLiveData<Response<Boolean>> response = new MutableLiveData<>();

    private final CompositeDisposable disposables = new CompositeDisposable();

    LoginViewModel(LoginUseCase loginUseCase,
                   SchedulersFacade schedulersFacade) {
        this.loginUseCase = loginUseCase;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    protected void onCleared(){}

    MutableLiveData<Response<Boolean>> getResponse() {
        return response;
    }

    void login(String userName, String password){
        /*LoginUseCase.LoginParam param = new LoginUseCase.LoginParam(userName, password);
        disposables.add(
                loginUseCase.buildUseCaseObservable(param)
                        .subscribe(,)
        );*/
    }
}
