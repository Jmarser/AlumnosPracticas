package com.jmarser.alumnospracticas_1.login.presenter;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;
import com.jmarser.alumnospracticas_1.login.interactor.LoginInteractor;
import com.jmarser.alumnospracticas_1.login.view.LoginView;
import com.jmarser.alumnospracticas_1.login.view.SplashView;
import com.jmarser.alumnospracticas_1.util.Constantes;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import javax.inject.Inject;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnGetLoginCallBack {

    @Inject
    public LoginPresenterImpl() {
    }

    @Inject
    SharedPreferences sharedPreferences;

    @Nullable
    @Inject
    LoginView loginView;

    @Nullable
    @Inject
    SplashView splashView;

    @Nullable
    @Inject
    ErrorView errorView;

    @Inject
    LoginInteractor interactor;

    private String email;
    private String password;


    @Override
    public void tryToLogin(TextInputLayout til_email, TextInputLayout til_password) {
        boolean email_not_empty = false;
        boolean pass_not_empty = false;

        email = til_email.getEditText().getText().toString().trim();
        password = til_password.getEditText().getText().toString().trim();

        if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_not_empty = true;
        }else{
            til_email.requestFocus();
            til_email.setError("Debe indicar un correo válido");
        }

        if(!TextUtils.isEmpty(password)){
            pass_not_empty = true;
        }else{
            if(email_not_empty) {
                til_password.requestFocus();
            }
            til_password.setError("Debe indicar el password");
        }

        if(email_not_empty && pass_not_empty){
            interactor.tryToLogin(email, password, this);
        }else{
            errorView.showError();
        }
    }

    @Override
    public void splashLogin() {
        email = sharedPreferences.getString(Constantes.INTENT_EMAIL, "");
        password = sharedPreferences.getString(Constantes.INTENT_PASSWORD, "");
        interactor.tryToLogin(email, password, this);
    }

    @Override
    public void onSuccessLogin(String email, String password) {
        sharedPreferences.edit().putString(Constantes.INTENT_EMAIL, email).apply();
        sharedPreferences.edit().putString(Constantes.INTENT_PASSWORD, password).apply();
        if(loginView != null) {
            loginView.goToLogin(email, password);
        }else{
            splashView.goToMain(email, password);
        }
    }

    @Override
    public void onErrorLogin() {
        if(loginView != null){
            errorView.showMessageError("Error de login");
        }else{
            sharedPreferences.edit().clear().apply();
            splashView.goToLogin();
        }
    }

}
