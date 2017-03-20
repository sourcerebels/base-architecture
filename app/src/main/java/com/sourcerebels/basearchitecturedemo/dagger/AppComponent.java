package com.sourcerebels.basearchitecturedemo.dagger;

import com.sourcerebels.basearchitecturedemo.users.UsersActivity;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(UsersActivity activity);
}
