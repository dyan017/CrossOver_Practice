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
    private final CompositeDisposable disposables;
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    public CompletableUseCase(ThreadExecutor executor, PostExecutionThread postThread) {
        this.disposables = new CompositeDisposable();
        threadExecutor = executor;
        postExecutionThread = postThread;
    }

    /**
     * Builds an {@link Completable} which will be used when executing the current {@link CompletableUseCase}.
     */
    protected abstract Completable buildCompletableUseCase(Params params);

    /**
     * Executes the current use case.
     *
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public void execute(Action onComplete, Consumer<? super Throwable> onError, Params params) {
        final Completable completable= this.buildCompletableUseCase(params);
        addDisposable(completable.subscribeOn(Schedulers.from(threadExecutor))
                .subscribeOn(postExecutionThread.getScheduler())
                .subscribe(onComplete, onError));
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }
}
