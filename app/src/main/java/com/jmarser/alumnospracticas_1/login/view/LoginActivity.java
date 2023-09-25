package com.jmarser.alumnospracticas_1.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.jmarser.alumnospracticas_1.R;
import com.jmarser.alumnospracticas_1.databinding.ActivityLoginBinding;
import com.jmarser.alumnospracticas_1.di.appComponent.AppComponent;
import com.jmarser.alumnospracticas_1.di.appComponent.DaggerAppComponent;
import com.jmarser.alumnospracticas_1.di.appModule.AppModule;
import com.jmarser.alumnospracticas_1.di.appModule.SharedPreferencesModule;
import com.jmarser.alumnospracticas_1.login.presenter.LoginPresenter;
import com.jmarser.alumnospracticas_1.main.MainActivity;
import com.jmarser.alumnospracticas_1.util.Constantes;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginView, ErrorView, View.OnClickListener, View.OnFocusChangeListener {

    private ActivityLoginBinding binding;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initInjection();
        initListener();
    }

    /**
     * Método con el que cargamos dagger para implementar la inyección de dependencias
     * */
    private void initInjection(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();

        appComponent.inject(this);
    }

    private void initListener(){
        binding.btnLogin.setOnClickListener(this);
        binding.tilEmail.getEditText().setOnFocusChangeListener(this);
        binding.tilPassword.getEditText().setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View v) {

        int idView = v.getId();

        if(idView == binding.btnLogin.getId()){
            showProgresBar();
            presenter.tryToLogin(binding.tilEmail, binding.tilPassword);
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int idView = v.getId();

        if(idView == binding.tilEmail.getEditText().getId()){
            if(hasFocus){
                binding.tilEmail.setError(null);
            }
        }

        if(idView == binding.tilPassword.getEditText().getId()){
            if(hasFocus){
                binding.tilPassword.setError(null);
            }
        }
    }

    @Override
    public void goToLogin(String email, String password) {
        Toast.makeText(this, "Login correcto", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra(Constantes.INTENT_EMAIL, email);
        intent.putExtra(Constantes.INTENT_PASSWORD, password);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError() {
        hideProgresBar();
    }

    @Override
    public void showMessageError(String message) {
        hideProgresBar();
        clearFormLogin();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void showProgresBar(){
        binding.btnLogin.setVisibility(View.INVISIBLE);
        binding.pbLogin.setVisibility(View.VISIBLE);
    }

    private void hideProgresBar(){
        binding.btnLogin.setVisibility(View.VISIBLE);
        binding.pbLogin.setVisibility(View.INVISIBLE);
    }

    private void clearFormLogin(){
        binding.tilEmail.getEditText().setText("");
        binding.tilPassword.getEditText().setText("");
        binding.tilEmail.getEditText().requestFocus();
    }
}