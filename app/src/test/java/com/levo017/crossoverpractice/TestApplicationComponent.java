package com.levo017.crossoverpractice;

import android.app.Application;


import com.levo017.crossoverpractice.features.login.LoginViewModelFactory;
import com.levo017.crossoverpractice.injection.ApplicationComponent;
import com.levo017.crossoverpractice.injection.BuilderModule;
import com.levo017.crossoverpractice.repositories.ApplicationRepository;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created by dyan017 on 4/12/2018.
 */
@Singleton
@Component(modules={
        AndroidSupportInjectionModule.class,
        TestAppModule.class,
        TestBuilderModule.class})
interface TestApplicationComponent extends ApplicationComponent{
    @Override
    void inject(DaggerApplication instance);

    ApplicationRepository ApplicationRepository();

    @Component.Builder
    interface Builder{
        @BindsInstance
        TestApplicationComponent.Builder application(TestApplication application);

        @BindsInstance
        TestApplicationComponent.Builder LoginViewModelFactory(LoginViewModelFactory factory);

        TestApplicationComponent build();

    }

}
