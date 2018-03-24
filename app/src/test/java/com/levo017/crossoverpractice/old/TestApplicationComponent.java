package com.levo017.crossoverpractice.old;

import android.app.Application;

import com.levo017.crossoverpractice.injection.AppModule;
import com.levo017.crossoverpractice.injection.ApplicationComponent;
import com.levo017.crossoverpractice.injection.BuilderModule;
import com.levo017.crossoverpractice.injection.CrossoverPracticeApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created by dyan017 on 3/9/2018.
 */
/*@Singleton
@Component(modules={
        AndroidSupportInjectionModule.class,
        TestAppModule.class,
        BuilderModule.class})
public interface TestApplicationComponent extends AndroidInjector<DaggerApplication> {
    void inject(TestApplicationComponent application);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder{
        @BindsInstance
        TestApplicationComponent.Builder application(Application application);
        TestApplicationComponent build();
    }
}*/
