package com.sourcerebels.basearchitecturedemo.users;

import com.sourcerebels.basearchitecture.interactor.Executor;
import com.sourcerebels.basearchitecture.interactor.Interactor;
import com.sourcerebels.basearchitecture.interactor.InteractorExecutionException;
import com.sourcerebels.basearchitecture.interactor.MainThread;
import com.sourcerebels.basearchitecturedemo.model.User;
import com.sourcerebels.basearchitecturedemo.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

public class LoadUsersInteractor extends Interactor<List<User>> {

    private UserRepository userRepository;

    @Inject
    public LoadUsersInteractor(Executor executor, MainThread mainThread,
                               UserRepository userRepository) {
        super(executor, mainThread);
        this.userRepository = userRepository;
    }

    @Override
    public List<User> runOnBackground() throws InteractorExecutionException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //Do nothing
        }
        //throw new InteractorExecutionException("Service not available");
        return userRepository.findAll();
    }
}
