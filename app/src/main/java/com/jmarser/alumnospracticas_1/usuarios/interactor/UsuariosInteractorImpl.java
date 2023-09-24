package com.jmarser.alumnospracticas_1.usuarios.interactor;


import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.api.wsApi.WsApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuariosInteractorImpl implements UsuariosInteractor{

    @Inject
    WsApi wsApi;

    @Inject
    public UsuariosInteractorImpl() {
    }

    @Override
    public void getUsers(OnGetUsersCallBack callBack) {
        Call<List<User>> call = wsApi.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    callBack.onSuccessGetUsers(new ArrayList<User>(response.body()));
                }else{
                    callBack.onErrorGetUsers();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callBack.onErrorServer();
            }
        });
    }
}
