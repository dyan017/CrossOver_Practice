package com.levo017.crossoverpractice.ui;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by dyan017 on 3/3/2018.
 */
@Module
public abstract class MainActivityModule {
    @Binds
    abstract MainView provideMainView(MainActivity mainActivity);


}
