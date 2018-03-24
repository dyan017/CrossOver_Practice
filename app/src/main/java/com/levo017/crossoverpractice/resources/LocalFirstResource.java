package com.levo017.crossoverpractice.resources;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.levo017.crossoverpractice.executors.PostExecutionThread;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by dyan017 on 1/8/2018.
 */

public abstract class LocalFirstResource<ResultType, NetworkResultType, NetworkRequestType> {
    private final PostExecutionThread postExecutionThread;
    private Subject<Resource<ResultType>> result = PublishSubject.create();
    private Resource<ResultType> actualResult;

    LocalFirstResource(PostExecutionThread executionThread) {
        this.postExecutionThread = executionThread;

        actualResult = Resource.loading(null);
        result.onNext(actualResult);

        getLocalResource().subscribeOn(executionThread.getScheduler())
                .subscribe(new Observer<ResultType>() {
                    ResultType resourceResult ;
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultType result) {
                        if (shouldUpdate(result)){
                            getNetworkResource(convertResultToNetworkRequest(result))
                                    .map(apiResponse -> {
                                        if (apiResponse.isSuccessful()){
                                            return convertNetworkResult(apiResponse.body);
                                        } else {
                                            return result;
                                        }
                                    }).doOnNext(updatedResult -> saveResultToLocal(updatedResult));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //On error, fallback to local persistence copy.

                    }

                    @Override
                    public void onComplete() {
                            actualResult= Resource.success(resourceResult);
                            result.onComplete();
                            saveResultToLocal(resourceResult);
                    }

                 });
    }

    @WorkerThread
    protected abstract void saveResultToLocal(@NonNull ResultType item);

    @NonNull
    @MainThread
    protected abstract Observable<ResultType> getLocalResource();

    protected abstract boolean shouldUpdate(ResultType result);

    @NonNull
    @MainThread
    protected abstract Observable<ApiResponse<NetworkResultType>> getNetworkResource(NetworkRequestType request);

    protected abstract NetworkRequestType convertResultToNetworkRequest(ResultType result);

    protected abstract ResultType convertNetworkResult(NetworkResultType networkResult);
}
