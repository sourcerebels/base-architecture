package com.sourcerebels.basearchitecture.interactor;

public class InteractorExecutionException extends Exception {

    public InteractorExecutionException() {
        super();
    }

    public InteractorExecutionException(String message) {
        super(message);
    }

    public InteractorExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InteractorExecutionException(Throwable cause) {
        super(cause);
    }
}
