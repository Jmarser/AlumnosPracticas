package com.jmarser.alumnospracticas_1.usuarios.presenter;

import com.jmarser.alumnospracticas_1.api.models.Comment;
import com.jmarser.alumnospracticas_1.usuarios.interactor.UsuariosInteractor;
import com.jmarser.alumnospracticas_1.usuarios.view.CommentsView;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;

public class CommentsPresenterImpl implements CommentsPresenter, UsuariosInteractor.OnGetCommentsCallBack {

    @Nullable
    @Inject
    CommentsView commentsView;

    @Nullable
    @Inject
    ErrorView errorView;

    @Inject
    UsuariosInteractor interactor;

    @Inject
    public CommentsPresenterImpl() {
    }

    @Override
    public void getCommentsForPostId(int postId) {
        interactor.getCommentsForPostId(postId, this);
    }

    @Override
    public void onSuccessGetComments(ArrayList<Comment> listadoComentarios) {
        commentsView.setComments(listadoComentarios);
    }

    @Override
    public void onErrorGetComments() {
        errorView.showError();
    }

}
