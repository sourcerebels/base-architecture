package com.sourcerebels.basearchitecture.interactor;

import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.ref.WeakReference;

import static android.content.ContentValues.TAG;

public abstract class Interactor<Result> implements Runnable {

    private final Executor executor;
    private final MainThread mainThread;
    private WeakReference<InteractorCallback<Result>> callback;

    public Interactor(Executor executor, MainThread mainThread) {
        this.executor = executor;
        this.mainThread = mainThread;
    }

    public abstract Result runOnBackground() throws InteractorExecutionException;

    @Override
    public void run() {
        try {
            postResult(runOnBackground());
        } catch (InteractorExecutionException e) {
            Log.d(TAG, "run: There was an error executing interactor", e);
            postError(e);
        }
    }

    public void execute(InteractorCallback<Result> callback) {
        this.callback = new WeakReference<>(callback);
        executor.run(this);
    }

    private void postResult(final Result result) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                InteractorCallback<Result> callback = getCallback();
                if (callback != null) {
                    callback.onExecutionSuccess(result);
                }
            }
        });
    }

    private void postError(final InteractorExecutionException error) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                InteractorCallback<Result> callback = getCallback();
                if (callback != null) {
                    callback.onExecutionError(error);
                }
            }
        });
    }

    @Nullable
    private InteractorCallback<Result> getCallback() {
        return (this.callback != null) ? this.callback.get() : null;
    }
}
