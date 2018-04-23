package com.levo017.crossoverpractice;

import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.levo017.crossoverpractice.features.login.LoginViewModel;
import com.levo017.crossoverpractice.features.login.LoginViewModelFactory;
import com.levo017.crossoverpractice.features.login.views.LoginActivity;
import com.levo017.crossoverpractice.models.User;
import com.levo017.crossoverpractice.resources.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import dagger.android.ContributesAndroidInjector;
import io.reactivex.Maybe;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by dyan017 on 4/9/2018.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22, application = TestApplication.class)
public class LoginActivityUnitTest {
    LoginActivity activity ;
    @Mock LoginViewModelFactory factory;
    @Mock LoginViewModel viewModel;

    private EditText passwordView;

    @Before
    public void setUp() throws Exception
    {
        initMocks(this);

    }

    @Test
    public void activityLaunches(){
        stubApplicationRepositoryReturnUser();
        activity.startActivity(new Intent());
    }

    @Test
    public void successLoginDisplaySuccessResponse(){
        stubApplicationRepositoryReturnUser();
        activity = Robolectric.setupActivity(LoginActivity.class);
        activity.startActivity(new Intent());
    }

    @Test
    public void errorLoginDisplayErrorResponse(){
        stubApplicationRepositoryReturnError();
        activity = Robolectric.setupActivity(LoginActivity.class);
        activity.startActivity(new Intent());
        passwordView = activity.findViewById(R.id.password);

        assertEquals(passwordView.getError(), "Password Incorrect");
    }

    @Test
    public void successLoginThenPostValueToError(){
        MutableLiveData<Resource<Boolean>> value = new MutableLiveData<>();
        value.setValue(Resource.success(true));
        when(((TestApplication)RuntimeEnvironment.application).mockViewModel.getLoginResponse()).thenReturn(value);
        activity = Robolectric.setupActivity(LoginActivity.class);
        activity.startActivity(new Intent());
        passwordView = activity.findViewById(R.id.password);
        assertNull(passwordView.getError());
        value.postValue(Resource.error("error message", false));
        assertEquals(passwordView.getError(), "Password Incorrect");


    }

    private void stubApplicationRepositoryReturnEmpty(){
        //when(((TestApplication)RuntimeEnvironment.application).AppComponent().ApplicationRepository().findUsersByUserName(any(String.class))).thenReturn(Maybe.empty());
    }

    private void stubApplicationRepositoryReturnError(){
        //when(((TestApplication)RuntimeEnvironment.application).AppComponent().ApplicationRepository().findUsersByUserName(any(String.class))).thenReturn(Maybe.error(new Exception()));
        MutableLiveData<Resource<Boolean>> value = new MutableLiveData<>();
        value.setValue(Resource.error("Error Message", false));
        when(((TestApplication)RuntimeEnvironment.application).mockViewModel.getLoginResponse()).thenReturn(value);
    }

    private void stubApplicationRepositoryReturnUser(){
        User validUser = new User();
        when(((TestApplication)RuntimeEnvironment.application).AppComponent().ApplicationRepository().findUsersByUserName(any(String.class))).thenReturn(Maybe.just(validUser));
    }
}

