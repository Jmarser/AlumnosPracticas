package com.jmarser.alumnospracticas_1.usuarios.presenter;

import com.jmarser.alumnospracticas_1.api.models.Post;
import com.jmarser.alumnospracticas_1.usuarios.interactor.UsuariosInteractor;
import com.jmarser.alumnospracticas_1.usuarios.view.UsuarioDetailsView;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;


public class UserDetailsPresenterImpl implements UserDetailsPresenter, UsuariosInteractor.OnGetPostsForUserCallBack {

    @Inject
    UsuariosInteractor interactor;

    @Nullable
    @Inject
    UsuarioDetailsView usuarioDetailsView;

    @Nullable
    @Inject
    ErrorView errorView;

    @Inject
    public UserDetailsPresenterImpl() {
    }

    @Override
    public void getPostsForUser() {
        interactor.getPostForUser(this);
    }

    @Override
    public void onSuccessGetPostsForUser(ArrayList<Post> listadoPosts) {
        usuarioDetailsView.setPostsForUser(listadoPosts);
    }

    @Override
    public void onErrorGetPostsForUser() {
        errorView.showError();
    }

}
