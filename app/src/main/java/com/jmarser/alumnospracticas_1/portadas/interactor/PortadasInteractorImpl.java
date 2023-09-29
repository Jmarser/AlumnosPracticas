package com.jmarser.alumnospracticas_1.portadas.interactor;

import android.content.SharedPreferences;

import com.jmarser.alumnospracticas_1.api.models.Album;
import com.jmarser.alumnospracticas_1.api.models.Portada;
import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.api.wsApi.WsApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PortadasInteractorImpl implements PortadasInteractor{

    @Inject
    WsApi wsApi;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    public PortadasInteractorImpl() {
    }

    @Override
    public void getAllUsers(OnGetAllUsersCallBack callBack) {
        Call<List<User>> call = wsApi.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    callBack.onSuccessGetAllUsers(new ArrayList<User>(response.body()));
                }else{
                    callBack.onErrorGetAllUsers();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getAllAlbunes(OnGetAllAlbunesCallBack callBack) {
        Call<List<Album>> call = wsApi.getAllAlbums();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful()){
                    callBack.onSuccessGetAlbunes(new ArrayList<Album>(response.body()));
                }else{
                    callBack.onErrorGetAlbunes();
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getAlbunesForUser(int userId, OnGetAllAlbunesCallBack callBack) {
        Call<List<Album>> call = wsApi.getAlbumsForUser(userId);
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful()){
                    callBack.onSuccessGetAlbunes(new ArrayList<Album>(response.body()));
                }else{
                    callBack.onErrorGetAlbunes();
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getAllPortadas(OnGetPortadasCallBack callBack) {
        Call<List<Portada>> call = wsApi.getPortadas();
        call.enqueue(new Callback<List<Portada>>() {
            @Override
            public void onResponse(Call<List<Portada>> call, Response<List<Portada>> response) {
                if(response.isSuccessful()){
                    callBack.onSuccessGetPortadas(new ArrayList<Portada>(response.body()));
                }else{
                    callBack.onErrorGetPortadas();
                }
            }

            @Override
            public void onFailure(Call<List<Portada>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getPortadasForAlbum(int albumId, OnGetPortadasCallBack callBack) {
        Call<List<Portada>> call = wsApi.getPortadasForAlbum(albumId);
        call.enqueue(new Callback<List<Portada>>() {
            @Override
            public void onResponse(Call<List<Portada>> call, Response<List<Portada>> response) {
                if (response.isSuccessful()){
                    callBack.onSuccessGetPortadas(new ArrayList<Portada>(response.body()));
                }else{
                    callBack.onErrorGetPortadas();
                }
            }

            @Override
            public void onFailure(Call<List<Portada>> call, Throwable t) {

            }
        });
    }
}
