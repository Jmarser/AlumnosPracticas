package com.jmarser.alumnospracticas_1.albunes.presenter;

import androidx.annotation.Nullable;

import com.jmarser.alumnospracticas_1.albunes.interactor.AlbunesInteractor;
import com.jmarser.alumnospracticas_1.albunes.view.AlbunesView;
import com.jmarser.alumnospracticas_1.api.models.Album;
import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import java.util.ArrayList;

import javax.inject.Inject;

public class AlbunesPresenterImpl implements AlbunesPresenter, AlbunesInteractor.OnGetAllAlbunesCallback, AlbunesInteractor.OnGetAllUsersCallback, AlbunesInteractor.OnErrorServer{

    @Nullable
    @Inject
    AlbunesView albunesView;

    @Nullable
    @Inject
    ErrorView errorView;

    @Inject
    AlbunesInteractor albunesInteractor;

    @Inject
    public AlbunesPresenterImpl() {
    }

    @Override
    public void getAllUser() {
        albunesInteractor.getAllUsers(this, this);
    }

    @Override
    public void onSuccessGetAllUsers(ArrayList<User> listadoUsuarios) {
        albunesView.setGetAllUser(listadoUsuarios);
    }

    @Override
    public void onErrorGetAllUsers() {
        errorView.showError();
    }

    @Override
    public void getAllAlbunes() {
        albunesInteractor.getAllAlbunes(this, this);
    }

    @Override
    public void getAlbumsForUser(int userId) {
        albunesInteractor.getAlbunesForUser(userId, this, this);
    }

    @Override
    public void onSuccessGetAllAlbunes(ArrayList<Album> listadoAlbunes) {
        albunesView.setGetAllAlbums(listadoAlbunes);
    }

    @Override
    public void onErrorGetAllAlbunes() {
        errorView.showError();
    }

    @Override
    public void onErrorServer() {
        albunesView.ErrorServer();
    }

}
