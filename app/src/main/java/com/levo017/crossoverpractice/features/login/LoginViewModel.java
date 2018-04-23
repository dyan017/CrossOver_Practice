package com.levo017.crossoverpractice.features.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.levo017.crossoverpractice.executors.SchedulersFacade;
import com.levo017.crossoverpractice.features.login.iterators.LoginUseCase;
import com.levo017.crossoverpractice.resources.Resource;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by dyan017 on 3/12/2018.
 */

public class LoginViewModel extends ViewModel {

    private final SchedulersFacade schedulersFacade;
    private final LoginUseCase loginUseCase;

    private final MutableLiveData<Resource<Boolean>> response = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadingStatus = new MutableLiveData<>();

    private final CompositeDisposable disposables = new CompositeDisposable();

    public LoginViewModel(LoginUseCase loginUseCase,
                   SchedulersFacade schedulersFacade) {
        this.loginUseCase = loginUseCase;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    protected void onCleared(){}

    public MutableLiveData<Resource<Boolean>> getLoginResponse() {
        return response;
    }

    public MutableLiveData<Boolean> getLoadingStatus() {
        return loadingStatus;
    }

    public void login(String userName, String password){
        LoginUseCase.LoginParam param = new LoginUseCase.LoginParam(userName, password);
        disposables.add(
                loginUseCase.buildUseCase(param)
                        .doOnSubscribe(s -> loadingStatus.setValue(true))
                        .doAfterTerminate(() -> loadingStatus.setValue(false))
                        .subscribe(
                            loginResult -> response.postValue(Resource.success(loginResult))
                        )
        );
    }

}
