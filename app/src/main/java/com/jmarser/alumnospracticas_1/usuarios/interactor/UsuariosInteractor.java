package com.jmarser.alumnospracticas_1.usuarios.interactor;

import com.jmarser.alumnospracticas_1.api.models.Comment;
import com.jmarser.alumnospracticas_1.api.models.Post;
import com.jmarser.alumnospracticas_1.api.models.Task;
import com.jmarser.alumnospracticas_1.api.models.User;

import java.util.ArrayList;

public interface UsuariosInteractor {

    void getUsers(OnGetUsersCallBack callBack);

    interface OnGetUsersCallBack{
        void onSuccessGetUsers(ArrayList<User> listadoUsuarios);
        void onErrorGetUsers();
        void onErrorServer();
    }

    void getPosts(int userId, OnGetPostsCallBack callBack);

    interface OnGetPostsCallBack {
        void onSuccessGetPosts(ArrayList<Post> listadoPosts);
        void onErrorGetPosts();
    }

    void getTodos(OnGetTodosCallBack callBack);

    interface OnGetTodosCallBack{
        void onSuccessGetTodos(ArrayList<Task> listadoTasks);
        void onErrorGetTodos();
    }

    void getCommentsForPostId(int postId, OnGetCommentsCallBack callBack);

    interface OnGetCommentsCallBack{
        void onSuccessGetComments(ArrayList<Comment> listadoComentarios);
        void onErrorGetComments();
    }
}
