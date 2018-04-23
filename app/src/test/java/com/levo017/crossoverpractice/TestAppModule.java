package com.levo017.crossoverpractice;

import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.SchedulersFacade;
import com.levo017.crossoverpractice.executors.ThreadExecutor;
import com.levo017.crossoverpractice.injection.BuilderModule_BindMainActivity;
import com.levo017.crossoverpractice.repositories.ApplicationRepository;

import org.mockito.Mock;
import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by dyan017 on 4/12/2018.
 */

@Module(subcomponents = {BuilderModule_BindMainActivity.MainActivitySubcomponent.class})
public class TestAppModule {

    //@Mock ThreadExecutor mockThreadExecutor;
    //@Mock PostExecutionThread mockPostExecutionThread;
    //@Mock SchedulersFacade mockSchedulerFacade;

    /*TestAppModule(){
        initMocks(this);
    }*/

    @Provides
    public ApplicationRepository provideMockRepository(){
        return Mockito.mock(ApplicationRepository.class);
    }

    @Provides
    public ThreadExecutor provideMockThreadExecutor(){
        return Mockito.mock(ThreadExecutor.class);
    }

    @Provides
    public PostExecutionThread provideMockPostExecutionThread(){
        return Mockito.mock(PostExecutionThread.class);
    }

    @Provides
    public SchedulersFacade provideMockSchedulersFacade(){
        return Mockito.mock(SchedulersFacade.class);
    }
}
