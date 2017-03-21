package com.sourcerebels.basearchitecturedemo.users;

import com.sourcerebels.basearchitecture.interactor.Executor;
import com.sourcerebels.basearchitecture.interactor.Interactor;
import com.sourcerebels.basearchitecture.interactor.InteractorCallback;
import com.sourcerebels.basearchitecture.interactor.InteractorExecutionException;
import com.sourcerebels.basearchitecture.interactor.MainThread;
import com.sourcerebels.basearchitecturedemo.model.User;
import com.sourcerebels.basearchitecturedemo.repository.UserRepository;

import javax.inject.Inject;

public class SaveUserInteractor extends Interactor<User> {

    private String userName;
    private UserRepository userRepository;

    @Inject
    public SaveUserInteractor(Executor executor, MainThread mainThread,
                              UserRepository userRepository) {
        super(executor, mainThread);
        this.userRepository = userRepository;
    }

    public void execute(String userName, InteractorCallback<User> callback) {
        this.userName = userName;
        super.execute(callback);
    }

    @Override
    public User runOnBackground() throws InteractorExecutionException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //Do nothing
        }
        return userRepository.save(new User(userName));
    }
}
