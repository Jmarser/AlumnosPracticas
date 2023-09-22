package com.jmarser.alumnospracticas_1.login.interactor;

import android.os.Handler;

import javax.inject.Inject;

public class LoginInteractorImpl  implements LoginInteractor{

    @Inject
    public LoginInteractorImpl() {
    }

    @Override
    public void tryToLogin(String email, String password, OnGetLoginCallBack callBack) {
        // Retrasamos la validación para simular una conexión con base de datos
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(email.equals("Correo@gmail.es") && password.equals("123456")){
                    callBack.onSuccessLogin(email, password);
                }else{
                    callBack.onErrorLogin();
                }
            }
        }, 3000);
    }
}
