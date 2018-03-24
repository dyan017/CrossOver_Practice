package com.levo017.crossoverpractice.executors;

import io.reactivex.Scheduler;

/**
 * Created by dyan017 on 1/7/2018.
 */

public interface PostExecutionThread {
    Scheduler getScheduler();
}
