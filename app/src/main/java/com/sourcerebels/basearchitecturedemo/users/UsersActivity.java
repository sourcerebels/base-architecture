package com.sourcerebels.basearchitecturedemo.users;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.sourcerebels.basearchitecture.presenter.BasePresenterActivity;
import com.sourcerebels.basearchitecturedemo.R;
import com.sourcerebels.basearchitecturedemo.dagger.AppComponent;
import com.sourcerebels.basearchitecturedemo.dagger.AppModule;
import com.sourcerebels.basearchitecturedemo.dagger.DaggerAppComponent;
import com.sourcerebels.basearchitecturedemo.model.User;
import com.sourcerebels.basicdialogs.AlertDialogFragment;
import com.sourcerebels.basicdialogs.ProgressDialogFragment;
import com.sourcerebels.basicdialogs.UserInputDialogFragment;
import com.sourcerebels.swiperefreshprogressview.SwipeRefreshProgressView;

import java.util.List;

import javax.inject.Inject;

public class UsersActivity extends BasePresenterActivity<UsersPresenter>
        implements UsersPresenter.View, UserInputDialogFragment.UserInputDialogListener {

    private static final String TAG = "MainActivity";

    private static final int REQUEST_CODE_NEW_USER = 1;

    @Inject
    UsersPresenter presenter;

    private RecyclerView recyclerView;
    private SwipeRefreshProgressView progress;
    private UsersRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        recyclerView = (RecyclerView) findViewById(R.id.users_recycler_view);
        progress = (SwipeRefreshProgressView) findViewById(R.id.users_progress_view);
        progress.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        findViewById(R.id.users_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInputDialogFragment.show(getSupportFragmentManager(),
                        getString(R.string.user_input),
                        getString(R.string.enter_user_name),
                        REQUEST_CODE_NEW_USER);
            }
        });
        AppComponent component = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
        component.inject(this);
        setPresenter(presenter);
        progress.setVisibility(View.VISIBLE);
        refresh();
    }

    @Override
    public void onUsersLoaded(List<User> users) {
        adapter = new UsersRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setUsers(users);
        progress.hide();
    }

    @Override
    public void onUsersLoadError(String errorMessage) {
        progress.showErrorMessage(getString(R.string.error_loading_users, errorMessage));
    }

    @Override
    public void onUserInput(int requestCode, String input) {
        if (requestCode == REQUEST_CODE_NEW_USER && !TextUtils.isEmpty(input)) {
            Log.d(TAG, "onUserInput: " + input);
            ProgressDialogFragment.show(getSupportFragmentManager(),
                    getString(R.string.working),
                    getString(R.string.adding_user, input));
            presenter.saveUser(input);
        }
    }

    @Override
    public void onUserSaved(User user) {
        ProgressDialogFragment.hide(getSupportFragmentManager());
        adapter.add(user);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void onUserSaveError(String errorMessage) {
        ProgressDialogFragment.hide(getSupportFragmentManager());
        AlertDialogFragment.show(getSupportFragmentManager(),
                getString(R.string.error),
                getString(R.string.error_adding_user));
    }

    private void refresh() {
        progress.showProgress();
        presenter.loadUsers();
    }
}
