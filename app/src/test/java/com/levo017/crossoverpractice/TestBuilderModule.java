package com.levo017.crossoverpractice;

import com.levo017.crossoverpractice.features.conferences.ConferenceModule;
import com.levo017.crossoverpractice.features.login.LoginModule;
import com.levo017.crossoverpractice.features.login.views.LoginActivity;
import com.levo017.crossoverpractice.features.session.SessionModule;
import com.levo017.crossoverpractice.features.topics.TopicsModule;
import com.levo017.crossoverpractice.features.users.UsersModule;
import com.levo017.crossoverpractice.ui.MainActivity;
import com.levo017.crossoverpractice.ui.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by dyan017 on 4/22/2018.
 */
@Module
public abstract class TestBuilderModule  {
    @ContributesAndroidInjector(modules = {MainActivityModule.class, ConferenceModule.class, SessionModule.class, TopicsModule.class, UsersModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules={TestLoginModule.class})
    abstract LoginActivity bindLoginActivity();
}
