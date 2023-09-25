package com.jmarser.alumnospracticas_1.usuarios.presenter;

import androidx.annotation.Nullable;

import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.usuarios.interactor.UsuariosInteractor;
import com.jmarser.alumnospracticas_1.usuarios.view.UsuariosView;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import java.util.ArrayList;

import javax.inject.Inject;

public class UsuariosPresenterImpl implements UsuariosPresenter, UsuariosInteractor.OnGetUsersCallBack {

    @Inject
    UsuariosInteractor interactor;

    @Nullable
    @Inject
    UsuariosView usuariosView;

    @Nullable
    @Inject
    ErrorView errorView;

    @Inject
    public UsuariosPresenterImpl() {

    }

    @Override
    public void getUsers() {
        interactor.getUsers(this);
    }

    @Override
    public void onSuccessGetUsers(ArrayList<User> listadoUsuarios) {
        usuariosView.setUsers(listadoUsuarios);
    }

    @Override
    public void onErrorGetUsers() {
        errorView.showError();
    }

    @Override
    public void onErrorServer() {
        usuariosView.errorServer();
    }

}
