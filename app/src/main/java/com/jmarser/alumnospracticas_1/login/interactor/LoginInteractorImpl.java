package com.jmarser.alumnospracticas_1.login.interactor;

import javax.inject.Inject;

public class LoginInteractorImpl  implements LoginInteractor{

    @Inject
    public LoginInteractorImpl() {
    }

    @Override
    public void tryToLogin(String email, String password, OnGetLoginCallBack callBack) {
        if(email.equals("Correo@gmail.es") && password.equals("123456")){
            callBack.onSuccessLogin(email, password);
        }else{
            callBack.onErrorLogin();
        }
    }
}
