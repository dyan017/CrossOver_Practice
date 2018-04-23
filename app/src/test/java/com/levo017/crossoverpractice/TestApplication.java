package com.levo017.crossoverpractice;


import android.arch.lifecycle.MutableLiveData;

import com.levo017.crossoverpractice.features.login.LoginViewModel;
import com.levo017.crossoverpractice.features.login.LoginViewModelFactory;
import com.levo017.crossoverpractice.resources.Resource;


import org.mockito.Mock;

import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.DaggerApplication;

import static org.mockito.Matchers.any;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by dyan017 on 3/9/2018.
 */

public class TestApplication extends DaggerApplication implements HasActivityInjector {

    TestApplicationComponent _appComponent;
    TestApplicationComponent AppComponent(){
        return _appComponent;
    }

    @Mock LoginViewModelFactory factory;
    @Mock public LoginViewModel mockViewModel;

    public TestApplication(){
        super();
        initMocks(this);
        when(factory.create(any())).thenReturn(mockViewModel);
        MutableLiveData<Boolean> mockStatus = new MutableLiveData<>();
        mockStatus.setValue(true);
        when(mockViewModel.getLoadingStatus()).thenReturn(mockStatus);

        MutableLiveData<Resource<Boolean>> mockLoginResponse = new MutableLiveData<>();
        mockLoginResponse.setValue(Resource.success(true));
        when(mockViewModel.getLoginResponse()).thenReturn(mockLoginResponse);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        TestApplicationComponent appComponent = DaggerTestApplicationComponent.builder().LoginViewModelFactory(factory).application(this).build();
        appComponent.inject(this);
        _appComponent = appComponent;
        return appComponent;
    }
}


