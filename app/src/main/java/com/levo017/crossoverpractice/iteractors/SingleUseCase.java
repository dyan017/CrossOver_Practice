package com.levo017.crossoverpractice.iteractors;

import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.ThreadExecutor;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dyan017 on 4/6/2018.
 */

public abstract class SingleUseCase<T, Params> {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    public SingleUseCase(ThreadExecutor executor, PostExecutionThread postThread) {
        threadExecutor = executor;
        postExecutionThread = postThread;
    }

    protected abstract Single buildUseCase(Params params);

    /**
     * Executes the current use case.
     *
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public Single execute(Params params) {
        final Single single= this.buildUseCase(params);
        single.subscribeOn(Schedulers.from(threadExecutor))
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        return single;
    }
}
