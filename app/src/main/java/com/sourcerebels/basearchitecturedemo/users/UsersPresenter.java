package com.sourcerebels.basearchitecturedemo.users;

import com.sourcerebels.basearchitecture.interactor.InteractorCallback;
import com.sourcerebels.basearchitecture.interactor.InteractorExecutionException;
import com.sourcerebels.basearchitecture.presenter.BaseView;
import com.sourcerebels.basearchitecture.presenter.Presenter;
import com.sourcerebels.basearchitecturedemo.model.User;

import java.util.List;

import javax.inject.Inject;

public class UsersPresenter extends Presenter<UsersPresenter.View> {

    private final LoadUsersInteractor loadUsersInteractor;
    private final SaveUserInteractor saveUserInteractor;

    @Inject
    public UsersPresenter(LoadUsersInteractor loadUsersInteractor,
                          SaveUserInteractor saveUserInteractor) {
        this.loadUsersInteractor = loadUsersInteractor;
        this.saveUserInteractor = saveUserInteractor;
    }

    public void loadUsers() {
        loadUsersInteractor.execute(new InteractorCallback<List<User>>() {
            @Override
            public void onExecutionSuccess(List<User> users) {
                View view = getView();
                if (view != null) {
                    view.onUsersLoaded(users);
                }
            }

            @Override
            public void onExecutionError(InteractorExecutionException error) {
                View view = getView();
                if (view != null) {
                    view.onUsersLoadError(error.getMessage());
                }
            }
        });
    }

    public void saveUser(String userName) {
        saveUserInteractor.execute(userName, new InteractorCallback<User>() {
            @Override
            public void onExecutionSuccess(User user) {
                View view = getView();
                if (view != null) {
                    view.onUserSaved(user);
                }
            }

            @Override
            public void onExecutionError(InteractorExecutionException error) {
                View view = getView();
                if (view != null) {
                    view.onUserSaveError(error.getMessage());
                }
            }
        });
    }

    public interface View extends BaseView {
        void onUsersLoaded(List<User> users);
        void onUsersLoadError(String errorMessage);
        void onUserSaved(User user);
        void onUserSaveError(String errorMessage);
    }
}
