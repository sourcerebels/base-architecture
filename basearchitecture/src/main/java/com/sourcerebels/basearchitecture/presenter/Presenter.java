package com.sourcerebels.basearchitecture.presenter;

public abstract class Presenter<T extends BaseView> {

    private T view;

    public void setView(T view) {
        this.view = view;
    }

    protected T getView() {
        return view;
    }

    public void dispose() {
        if (view != null) {
            view = null;
        }
    }
}
