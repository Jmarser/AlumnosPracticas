package com.jmarser.alumnospracticas_1.albunes.view;

import com.jmarser.alumnospracticas_1.api.models.Album;
import com.jmarser.alumnospracticas_1.api.models.User;

import java.util.ArrayList;

public interface AlbunesView {

    void setGetAllUser(ArrayList<User> listadoUsuarios);

    void setGetAllAlbums(ArrayList<Album> listadoAlbunes);

    void ErrorServer();


}
