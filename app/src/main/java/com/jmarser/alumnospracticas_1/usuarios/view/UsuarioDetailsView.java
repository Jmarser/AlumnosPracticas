package com.jmarser.alumnospracticas_1.usuarios.view;

import com.jmarser.alumnospracticas_1.api.models.Post;
import com.jmarser.alumnospracticas_1.api.models.Task;

import java.util.ArrayList;

public interface UsuarioDetailsView {

    void setPosts(ArrayList<Post> listadoPosts);

    void setTodos(ArrayList<Task> listadoTodos);
}
