package com.sourcerebels.basearchitecture.interactor;

public interface InteractorCallback<Result> {

    void onExecutionSuccess(Result result);

    void onExecutionError(InteractorExecutionException error);
}
