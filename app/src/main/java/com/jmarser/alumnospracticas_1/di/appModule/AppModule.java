package com.jmarser.alumnospracticas_1.di.appModule;


import android.content.Context;

import androidx.annotation.Nullable;

import com.jmarser.alumnospracticas_1.login.interactor.LoginInteractor;
import com.jmarser.alumnospracticas_1.login.interactor.LoginInteractorImpl;
import com.jmarser.alumnospracticas_1.login.presenter.LoginPresenter;
import com.jmarser.alumnospracticas_1.login.presenter.LoginPresenterImpl;
import com.jmarser.alumnospracticas_1.login.view.LoginActivity;
import com.jmarser.alumnospracticas_1.login.view.LoginView;
import com.jmarser.alumnospracticas_1.main.MainActivity;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import dagger.Module;
import dagger.Provides;

@Module()
public class AppModule {

    /* Propiedades */
    private LoginActivity loginActivity;
    private MainActivity mainActivity;

    private Context context;

    /* Constructores */

    public AppModule() {
    }

    public AppModule(LoginActivity loginActivity, Context context) {
        this.loginActivity = loginActivity;
        this.context = context;
    }

    public AppModule(MainActivity mainActivity, Context context) {
        this.mainActivity = mainActivity;
        this.context = context;
    }

    /* Views */

    @Nullable
    @Provides
    public LoginView loginActivity(){
        if(loginActivity != null){
            return loginActivity;
        }
        return null;
    }

    @Nullable
    @Provides
    public MainActivity mainActivity(){
        if(mainActivity != null){
            return mainActivity;
        }
        return mainActivity;
    }

    @Nullable
    @Provides
    public ErrorView provideErrorView(){
        if(loginActivity != null){
            return loginActivity;
        }
        return null;
    }

    /* Presenters */
    @Provides
    public LoginPresenter providesLoginPresenter(LoginPresenterImpl presenter){
        return presenter;
    }

    /* Interactors */
    @Provides
    public LoginInteractor providesLoginInteractor(LoginInteractorImpl interactor){
        return interactor;
    }
}
