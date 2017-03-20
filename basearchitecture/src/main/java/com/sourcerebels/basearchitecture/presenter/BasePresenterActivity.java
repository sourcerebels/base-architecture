package com.sourcerebels.basearchitecture.presenter;

import android.support.v7.app.AppCompatActivity;

public abstract class BasePresenterActivity<T extends Presenter> extends AppCompatActivity
        implements BaseView {

    private T presenter;

    @SuppressWarnings("unchecked")
    protected void setPresenter(T presenter) {
        this.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dispose();
        }
    }
}