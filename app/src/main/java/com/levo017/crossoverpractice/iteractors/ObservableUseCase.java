package com.levo017.crossoverpractice.iteractors;

import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.ThreadExecutor;
import com.levo017.crossoverpractice.utilities.Preconditions;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dyan017 on 1/7/2018.
 */

public abstract class ObservableUseCase<T, Params> {
    private final CompositeDisposable disposables;
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    public ObservableUseCase(ThreadExecutor executor, PostExecutionThread postThread) {
        this.disposables = new CompositeDisposable();
        threadExecutor = executor;
        postExecutionThread = postThread;
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link ObservableUseCase}.
     */
    public abstract Observable<T> buildUseCase(Params params);

    /**
     * Executes the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     * by {@link #buildUseCase(Params)} ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public void execute(DisposableObserver<T> observer, Params params) {
        Preconditions.checkNotNull(observer);
        final Observable<T> observable = this.buildUseCase(params);
        addDisposable(observable.subscribeOn(Schedulers.from(threadExecutor))
                .subscribeOn(postExecutionThread.getScheduler())
                .subscribeWith(observer));
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
