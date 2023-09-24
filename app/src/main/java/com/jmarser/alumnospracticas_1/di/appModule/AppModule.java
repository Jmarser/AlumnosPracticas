package com.jmarser.alumnospracticas_1.di.appModule;


import android.content.Context;

import androidx.annotation.Nullable;

import com.jmarser.alumnospracticas_1.albunes.view.AlbunesFragment;
import com.jmarser.alumnospracticas_1.albunes.view.AlbunesView;
import com.jmarser.alumnospracticas_1.login.interactor.LoginInteractor;
import com.jmarser.alumnospracticas_1.login.interactor.LoginInteractorImpl;
import com.jmarser.alumnospracticas_1.login.presenter.LoginPresenter;
import com.jmarser.alumnospracticas_1.login.presenter.LoginPresenterImpl;
import com.jmarser.alumnospracticas_1.login.view.LoginActivity;
import com.jmarser.alumnospracticas_1.login.view.LoginView;
import com.jmarser.alumnospracticas_1.login.view.SplashActivity;
import com.jmarser.alumnospracticas_1.login.view.SplashView;
import com.jmarser.alumnospracticas_1.main.MainActivity;
import com.jmarser.alumnospracticas_1.portadas.view.PortadasFragment;
import com.jmarser.alumnospracticas_1.portadas.view.PortadasView;
import com.jmarser.alumnospracticas_1.usuarios.interactor.UsuariosInteractor;
import com.jmarser.alumnospracticas_1.usuarios.interactor.UsuariosInteractorImpl;
import com.jmarser.alumnospracticas_1.usuarios.presenter.UsuariosPresenter;
import com.jmarser.alumnospracticas_1.usuarios.presenter.UsuariosPresenterImpl;
import com.jmarser.alumnospracticas_1.usuarios.view.UsuariosFragment;
import com.jmarser.alumnospracticas_1.usuarios.view.UsuariosView;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import dagger.Module;
import dagger.Provides;

@Module(includes = {SharedPreferencesModule.class, ConnectionModule.class})
public class AppModule {

    /* Propiedades */
    private SplashActivity splashActivity;
    private LoginActivity loginActivity;
    private MainActivity mainActivity;
    private UsuariosFragment usuariosFragment;
    private AlbunesFragment albunesFragment;
    private PortadasFragment portadasFragment;

    private Context context;

    /* Constructores */

    public AppModule() {
    }

    public AppModule(SplashActivity splashActivity, Context context) {
        this.splashActivity = splashActivity;
        this.context = context;
    }

    public AppModule(LoginActivity loginActivity, Context context) {
        this.loginActivity = loginActivity;
        this.context = context;
    }

    public AppModule(MainActivity mainActivity, Context context) {
        this.mainActivity = mainActivity;
        this.context = context;
    }

    public AppModule(UsuariosFragment usuariosFragment, Context context) {
        this.usuariosFragment = usuariosFragment;
        this.context = context;
    }

    public AppModule(AlbunesFragment albunesFragment, Context context) {
        this.albunesFragment = albunesFragment;
        this.context = context;
    }

    public AppModule(PortadasFragment portadasFragment, Context context) {
        this.portadasFragment = portadasFragment;
        this.context = context;
    }

    /* Views */

    @Nullable
    @Provides
    public SplashView splashActivity(){
        if(splashActivity != null){
            return splashActivity;
        }
        return splashActivity;
    }

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
    public UsuariosView usuariosFragment(){
        if(usuariosFragment != null){
            return usuariosFragment;
        }
        return null;
    }

    @Nullable
    @Provides
    public AlbunesView albunesFragment(){
        if(albunesFragment != null){
            return albunesFragment;
        }
        return null;
    }

    @Nullable
    @Provides
    public PortadasView portadasFragment(){
        if(portadasFragment != null){
            return portadasFragment;
        }
        return null;
    }

    @Nullable
    @Provides
    public ErrorView provideErrorView(){
        if(splashActivity != null){
            return splashActivity;
        }else if(loginActivity != null){
            return loginActivity;
        }else if(usuariosFragment != null){
            return usuariosFragment;
        }
        return null;
    }

    /* Presenters */
    @Provides
    public LoginPresenter providesLoginPresenter(LoginPresenterImpl presenter){
        return presenter;
    }

    @Provides
    public UsuariosPresenter providesUsuariosPresenter(UsuariosPresenterImpl presenter){
        return presenter;
    }

    /* Interactors */
    @Provides
    public LoginInteractor providesLoginInteractor(LoginInteractorImpl interactor){
        return interactor;
    }

    @Provides
    public UsuariosInteractor providesUsuariosInteractor(UsuariosInteractorImpl interactor){
        return interactor;
    }

}
