package com.levo017.crossoverpractice.iteractors;

import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.ThreadExecutor;
import com.levo017.crossoverpractice.models.Topic;
import com.levo017.crossoverpractice.repositories.ApplicationRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by dyan017 on 1/8/2018.
 */

public class GetTopics extends ObservableUseCase<List<Topic>, String> {
    ApplicationRepository repository;

    @Inject
    GetTopics(ApplicationRepository conferenceRepository, ThreadExecutor executor, PostExecutionThread postThread) {
        super(executor, postThread);
        this.repository = conferenceRepository;
    }

    @Override
    public Observable<List<Topic>> buildUseCase(String TopicId) {
        return null;
    }


}
