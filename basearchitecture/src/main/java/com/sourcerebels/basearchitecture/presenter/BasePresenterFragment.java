package com.sourcerebels.basearchitecture.presenter;

import android.support.v4.app.Fragment;

public abstract class BasePresenterFragment<T extends Presenter> extends Fragment
        implements BaseView {

    private T presenter;

    @SuppressWarnings("unchecked")
    protected void setPresenter(T presenter) {
        this.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dispose();
        }
    }
}
