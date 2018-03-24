package com.levo017.crossoverpractice.injection;

import android.app.Application;

import com.levo017.crossoverpractice.features.users.UsersModule;
import com.levo017.crossoverpractice.features.users.di.UserComponent;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created by dyan017 on 1/12/2018.
 */
@Singleton
@Component(modules={
        AndroidSupportInjectionModule.class,
        AppModule.class,
        BuilderModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {
    void inject(CrossoverPracticeApplication app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }

}
