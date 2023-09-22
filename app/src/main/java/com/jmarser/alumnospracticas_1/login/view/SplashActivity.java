package com.jmarser.alumnospracticas_1.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.jmarser.alumnospracticas_1.R;
import com.jmarser.alumnospracticas_1.databinding.ActivitySplashBinding;
import com.jmarser.alumnospracticas_1.di.appComponent.AppComponent;
import com.jmarser.alumnospracticas_1.di.appComponent.DaggerAppComponent;
import com.jmarser.alumnospracticas_1.di.appModule.AppModule;
import com.jmarser.alumnospracticas_1.di.appModule.SharedPreferencesModule;
import com.jmarser.alumnospracticas_1.login.presenter.LoginPresenter;
import com.jmarser.alumnospracticas_1.main.MainActivity;
import com.jmarser.alumnospracticas_1.util.Constantes;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements SplashView, ErrorView {

    private ActivitySplashBinding binding;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Quitamos la barra de notificaciones del sistema
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initInjection();

        // Programamos que pasados unos segundos realicemos la acción de login
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.splashLogin();
            }
        }, 3000);
    }

    private void initInjection(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();

        appComponent.inject(this);
    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void goToMain(String email, String password) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.putExtra(Constantes.INTENT_EMAIL, email);
        intent.putExtra(Constantes.INTENT_PASSWORD, password);
        startActivity(intent);
        finish();
    }


    @Override
    public void showError() {

    }

    @Override
    public void showMessageError(String message) {

    }
}