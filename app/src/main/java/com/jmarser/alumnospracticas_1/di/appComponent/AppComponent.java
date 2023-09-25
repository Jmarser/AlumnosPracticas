package com.jmarser.alumnospracticas_1.di.appComponent;

import com.jmarser.alumnospracticas_1.albunes.view.AlbunesFragment;
import com.jmarser.alumnospracticas_1.di.appModule.AppModule;
import com.jmarser.alumnospracticas_1.di.appModule.SharedPreferencesModule;
import com.jmarser.alumnospracticas_1.login.view.LoginActivity;
import com.jmarser.alumnospracticas_1.login.view.SplashActivity;
import com.jmarser.alumnospracticas_1.main.MainActivity;
import com.jmarser.alumnospracticas_1.portadas.view.PortadasFragment;
import com.jmarser.alumnospracticas_1.usuarios.view.UsuarioDetailActivity;
import com.jmarser.alumnospracticas_1.usuarios.view.UsuariosFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, SharedPreferencesModule.class})
public interface AppComponent {

    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(UsuariosFragment usuariosFragment);

    void inject(UsuarioDetailActivity usuarioDetailActivity);

    void inject(AlbunesFragment albunesFragment);

    void inject(PortadasFragment portadasFragment);
}
