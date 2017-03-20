package com.sourcerebels.basearchitecturedemo.dagger;

import com.sourcerebels.basearchitecture.logger.AndroidLogger;
import com.sourcerebels.basearchitecture.interactor.Executor;
import com.sourcerebels.basearchitecture.logger.Logger;
import com.sourcerebels.basearchitecture.interactor.MainThread;
import com.sourcerebels.basearchitecture.interactor.MainThreadImpl;
import com.sourcerebels.basearchitecture.interactor.ThreadExecutor;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public Logger logger() {
        return new AndroidLogger();
    }

    @Provides
    public MainThread mainThread() {
        return new MainThreadImpl();
    }

    @Provides
    public Executor threadExecutor() {
        return new ThreadExecutor();
    }
}
