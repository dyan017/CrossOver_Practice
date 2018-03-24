package com.levo017.crossoverpractice.executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by dyan017 on 3/4/2018.
 */

@Singleton
public class UIThread implements PostExecutionThread {
    @Inject
    UIThread() {};

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
