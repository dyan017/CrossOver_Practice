package com.levo017.crossoverpractice.resources;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.levo017.crossoverpractice.executors.PostExecutionThread;
import com.levo017.crossoverpractice.executors.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by dyan017 on 1/7/2018.
 */

public abstract class NetworkFirstResource<ResultType, NetworkResultType> {
    private final PostExecutionThread postExecutionThread;
    private Subject<Resource<ResultType>> result = PublishSubject.create();
    private Resource<ResultType> actualResult;

    NetworkFirstResource(PostExecutionThread executionThread){
        this.postExecutionThread = executionThread;

        actualResult = Resource.loading(null);
        result.onNext(actualResult);

        if (isNetworkAvailable()){
            getNetworkResource()
                    .subscribeOn(postExecutionThread.getScheduler())
                    .subscribe(new Observer<ApiResponse<NetworkResultType>>() {
                        ResultType resourceResult ;
                        @Override
                        public void onSubscribe(Disposable d) {

                }

                        @Override
                        public void onNext(ApiResponse<NetworkResultType> apiResponse) {
                            if (apiResponse.isSuccessful()) {
                                this.resourceResult = convertNetworkResult(apiResponse.body);
                                actualResult = Resource.loading(resourceResult);
                                result.onNext(actualResult);
                            } else {
                                // apiResponse not successful, fall back to local persistence
                                retrieveResourceFromLocal();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                    //On error, fallback to local persistence copy.
                    retrieveResourceFromLocal();
                }

                        @Override
                        public void onComplete() {
                            actualResult= Resource.success(resourceResult);
                            result.onComplete();
                            saveResultToLocal(resourceResult);
                        }
                    });
        } else {
            retrieveResourceFromLocal();
        }
    }

    private void retrieveResourceFromLocal(){
        getLocalResource().subscribe(new Observer<ResultType>() {
            ResultType resourceResult ;
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResultType resourceResult) {
                this.resourceResult = resourceResult;
                actualResult = Resource.loading(resourceResult);
                result.onNext(actualResult);
            }

            @Override
            public void onError(Throwable e) {
                actualResult = null;
                result.onError(e);
            }

            @Override
            public void onComplete() {
                actualResult= Resource.success(resourceResult);
                result.onComplete();
            }
        });
    }


    @WorkerThread
    protected abstract void saveResultToLocal(@NonNull ResultType item);

    @MainThread
    protected abstract boolean isNetworkAvailable();

    @NonNull
    @MainThread
    protected abstract Observable<ResultType> getLocalResource();

    @NonNull
    @MainThread
    protected abstract Observable<ApiResponse<NetworkResultType>> getNetworkResource();

    protected abstract ResultType convertNetworkResult(NetworkResultType networkResult);

}
