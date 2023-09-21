package com.jmarser.alumnospracticas_1.login.interactor;

public interface LoginInteractor {

    void tryToLogin(String email, String password, OnGetLoginCallBack callBack);

    interface OnGetLoginCallBack{
        void onSuccessLogin(String email, String password);
        void onErrorLogin();
    }
}
