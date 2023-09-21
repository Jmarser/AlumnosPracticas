package com.jmarser.alumnospracticas_1.di.appComponent;

import com.jmarser.alumnospracticas_1.di.appModule.AppModule;
import com.jmarser.alumnospracticas_1.login.view.LoginActivity;
import com.jmarser.alumnospracticas_1.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);
}
