package com.jmarser.alumnospracticas_1.api.wsApi;


import com.jmarser.alumnospracticas_1.api.models.Post;
import com.jmarser.alumnospracticas_1.api.models.Task;
import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.util.Constantes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface que contiene todas las llamadas a la api
 * */
public interface WsApi {

    @GET(Constantes.GET_USERS)
    Call<List<User>> getAllUsers();

    @GET(Constantes.GET_POSTS)
    Call<List<Post>> getPostsForUserId(@Query("userId") int userId);

    @GET(Constantes.GET_TODOS)
    Call<List<Task>> getTasks();


}
