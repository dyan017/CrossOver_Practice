package com.levo017.crossoverpractice.injection;

import android.app.Application;

import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by dyan017 on 3/7/2018.
 */

public class CrossoverPracticeApplication extends DaggerApplication implements HasActivityInjector {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent appComponent = DaggerApplicationComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

}

