package com.levo017.crossoverpractice.injection;

import android.app.Application;
import android.content.Context;

import com.levo017.crossoverpractice.executors.JobExecutor;
import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.ThreadExecutor;
import com.levo017.crossoverpractice.executors.UIThread;
import com.levo017.crossoverpractice.persistence.AppDatabase;
import com.levo017.crossoverpractice.repositories.ApplicationRepository;
import com.levo017.crossoverpractice.repositories.ApplicationRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by dyan017 on 3/7/2018.
 */

@Module(subcomponents = {BuilderModule_BindMainActivity.MainActivitySubcomponent.class})
public abstract class AppModule {
    @Provides
    @Singleton
    public static Context provideContext(Application application) {return application;}

    @Binds
    public abstract ApplicationRepository provideRepository(ApplicationRepositoryImpl implementation);

    @Provides
    public static AppDatabase provideAppDatabase(Context context){
        AppDatabase database = AppDatabase.create(context, false);
        return database;
    }

    @Binds
    public abstract ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor);

    @Binds
    public abstract PostExecutionThread providePostExecutionThread(UIThread uiThread);
}
