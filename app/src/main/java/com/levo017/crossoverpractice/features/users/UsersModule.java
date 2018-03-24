package com.levo017.crossoverpractice.features.users;

import com.levo017.crossoverpractice.features.users.views.AddUserFragment;
import com.levo017.crossoverpractice.features.users.views.AddUserView;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by dyan017 on 3/3/2018.
 */

@Module
public abstract class UsersModule {

    @Binds
    public abstract AddUserView provideAddUserView(AddUserFragment addUserFragment);


}
