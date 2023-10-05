package com.jmarser.alumnospracticas_1.usuarios.interactor;


import com.jmarser.alumnospracticas_1.api.models.Comment;
import com.jmarser.alumnospracticas_1.api.models.Post;
import com.jmarser.alumnospracticas_1.api.models.Task;
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
                    //callBack.onSuccessGetUsers(null);
                    //callBack.onErrorServer();
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

    @Override
    public void getPosts(int userId, OnGetPostsCallBack callBack) {
        Call<List<Post>> call = wsApi.getPostsForUserId(userId);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    callBack.onSuccessGetPosts(new ArrayList<Post>(response.body()));
                }else{
                    callBack.onErrorGetPosts();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callBack.onErrorGetPosts();
            }
        });
    }

    @Override
    public void getTodos(OnGetTodosCallBack callBack) {
        Call<List<Task>> call = wsApi.getTasks();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if(response.isSuccessful()){
                    callBack.onSuccessGetTodos(new ArrayList<Task>(response.body()));
                }else{
                    callBack.onErrorGetTodos();
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                callBack.onErrorGetTodos();
            }
        });
    }

    @Override
    public void getCommentsForPostId(int postId, OnGetCommentsCallBack callBack) {
        Call<List<Comment>> call = wsApi.getCommentsForPostId(postId);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(response.isSuccessful()){
                    callBack.onSuccessGetComments(new ArrayList<Comment>(response.body()));
                }else{
                    callBack.onErrorGetComments();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                callBack.onErrorGetComments();
            }
        });
    }
}
