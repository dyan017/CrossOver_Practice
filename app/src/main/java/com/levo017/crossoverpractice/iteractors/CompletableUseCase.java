package com.levo017.crossoverpractice.iteractors;

import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.ThreadExecutor;
import com.levo017.crossoverpractice.utilities.Preconditions;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dyan017 on 1/7/2018.
 */

public abstract class CompletableUseCase<Params> {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    public CompletableUseCase(ThreadExecutor executor, PostExecutionThread postThread) {
        threadExecutor = executor;
        postExecutionThread = postThread;
    }

    /**
     * Builds an {@link Completable} which will be used when executing the current {@link CompletableUseCase}.
     */
    protected abstract Completable buildUseCase(Params params);

    /**
     * Executes the current use case.
     *
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public Completable execute(Params params) {
        final Completable completable= this.buildUseCase(params);
        completable.subscribeOn(Schedulers.from(threadExecutor))
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        return completable;
    }
}
