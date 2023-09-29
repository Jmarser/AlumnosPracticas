package com.jmarser.alumnospracticas_1.portadas.presenter;

import androidx.annotation.Nullable;

import com.jmarser.alumnospracticas_1.api.models.Album;
import com.jmarser.alumnospracticas_1.api.models.Portada;
import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.portadas.interactor.PortadasInteractor;
import com.jmarser.alumnospracticas_1.portadas.view.PortadasView;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import java.util.ArrayList;

import javax.inject.Inject;

public class PortadasPresenterImpl implements PortadasPresenter, PortadasInteractor.OnGetPortadasCallBack, PortadasInteractor.OnGetAllAlbunesCallBack, PortadasInteractor.OnGetAllUsersCallBack{

    @Nullable
    @Inject
    PortadasView portadasView;

    @Nullable
    @Inject
    ErrorView errorView;

    @Inject
    PortadasInteractor portadasInteractor;

    @Inject
    public PortadasPresenterImpl() {
    }

    @Override
    public void getAllUsers() {
        portadasInteractor.getAllUsers(this);
    }

    @Override
    public void onSuccessGetAllUsers(ArrayList<User> usuarios) {
        portadasView.setUsuarios(usuarios);
    }

    @Override
    public void onErrorGetAllUsers() {

    }

    @Override
    public void getAllAlbunes() {
        portadasInteractor.getAllAlbunes(this);
    }

    @Override
    public void getAlbunesForUser(int userId) {
        portadasInteractor.getAlbunesForUser(userId, this);
    }

    @Override
    public void onSuccessGetAlbunes(ArrayList<Album> albunes) {
        portadasView.setAlbunes(albunes);
    }

    @Override
    public void onErrorGetAlbunes() {

    }

    @Override
    public void getAllPortadas() {
        portadasInteractor.getAllPortadas(this);
    }

    @Override
    public void getPortadasForAlbum(int albumId) {
        portadasInteractor.getPortadasForAlbum(albumId, this);
    }

    @Override
    public void onSuccessGetPortadas(ArrayList<Portada> portadas) {
        portadasView.setPortadas(portadas);
    }

    @Override
    public void onErrorGetPortadas() {

    }

}
