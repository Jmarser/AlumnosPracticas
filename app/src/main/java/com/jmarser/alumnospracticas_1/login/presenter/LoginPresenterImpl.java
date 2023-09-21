package com.jmarser.alumnospracticas_1.login.presenter;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;
import com.jmarser.alumnospracticas_1.login.interactor.LoginInteractor;
import com.jmarser.alumnospracticas_1.login.view.LoginView;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import javax.inject.Inject;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnGetLoginCallBack {

    @Inject
    public LoginPresenterImpl() {
    }

    @Nullable
    @Inject
    LoginView view;

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
            til_email.setError("Debe indicar un correo válido");
        }

        if(!TextUtils.isEmpty(password)){
            pass_not_empty = true;
        }else{
            til_password.setError("Debe indicar el password");
        }

        if(email_not_empty && pass_not_empty){
            interactor.tryToLogin(email, password, this);
        }else{
            errorView.showError();
        }
    }

    @Override
    public void onSuccessLogin(String email, String password) {
        view.goToLogin(email, password);
    }

    @Override
    public void onErrorLogin() {
        errorView.showMessageError("Error de login");
    }

}
