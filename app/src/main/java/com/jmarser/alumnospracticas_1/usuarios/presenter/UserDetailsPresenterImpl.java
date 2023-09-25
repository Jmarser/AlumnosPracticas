package com.jmarser.alumnospracticas_1.usuarios.presenter;

import com.jmarser.alumnospracticas_1.api.models.Post;
import com.jmarser.alumnospracticas_1.api.models.Task;
import com.jmarser.alumnospracticas_1.usuarios.interactor.UsuariosInteractor;
import com.jmarser.alumnospracticas_1.usuarios.view.UsuarioDetailsView;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;


public class UserDetailsPresenterImpl implements UserDetailsPresenter, UsuariosInteractor.OnGetPostsCallBack, UsuariosInteractor.OnGetTodosCallBack {

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
    public void getPosts() {
        interactor.getPosts(this);
    }

    @Override
    public void onSuccessGetPosts(ArrayList<Post> listadoPosts) {
        usuarioDetailsView.setPosts(listadoPosts);
    }

    @Override
    public void onErrorGetPosts() {
        errorView.showError();
    }

    @Override
    public void getTodos() {
        interactor.getTodos(this);
    }

    @Override
    public void onSuccessGetTodos(ArrayList<Task> listadoTasks) {
        usuarioDetailsView.setTodos(listadoTasks);
    }

    @Override
    public void onErrorGetTodos() {

    }
}
