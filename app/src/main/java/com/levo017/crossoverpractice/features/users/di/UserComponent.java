package com.levo017.crossoverpractice.features.users.di;

import com.levo017.crossoverpractice.features.users.UsersModule;
import com.levo017.crossoverpractice.features.users.views.AddUserView;

import dagger.Subcomponent;

/**
 * Created by dyan017 on 3/3/2018.
 */

@Subcomponent(modules = {UsersModule.class})
public interface UserComponent {
    //Here in user module, provides the user component.
    void inject(AddUserView addUserView);
}
