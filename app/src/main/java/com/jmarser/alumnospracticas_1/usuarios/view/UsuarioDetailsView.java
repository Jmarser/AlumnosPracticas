package com.jmarser.alumnospracticas_1.usuarios.view;

import com.jmarser.alumnospracticas_1.api.models.Post;

import java.util.ArrayList;

public interface UsuarioDetailsView {

    void setPostsForUser(ArrayList<Post> listadoPosts);
}
