package com.levo017.crossoverpractice;

import com.levo017.crossoverpractice.executors.SchedulersFacade;
import com.levo017.crossoverpractice.features.login.LoginViewModel;
import com.levo017.crossoverpractice.features.login.LoginViewModelFactory;
import com.levo017.crossoverpractice.features.login.iterators.LoginUseCase;
import com.levo017.crossoverpractice.features.login.views.LoginActivity;
import com.levo017.crossoverpractice.features.login.views.LoginView;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by dyan017 on 4/22/2018.
 */
@Module
public abstract class MockLoginModule {
    @Binds
    public abstract LoginView provideAddUserView(LoginActivity loginActivity);

    @Provides
    static LoginViewModelFactory provideLoginViewModelFactory(LoginUseCase loginUseCase,
                                                              SchedulersFacade schedulersFacade) {

        LoginViewModel mockLoginViewModel = mock(LoginViewModel.class);


        LoginViewModelFactory result;
        result = mock(LoginViewModelFactory.class);
        when(result.create(LoginViewModel.class)).thenReturn(null);
        return result;
    }
}
