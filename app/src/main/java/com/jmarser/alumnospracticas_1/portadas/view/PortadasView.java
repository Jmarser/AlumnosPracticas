package com.jmarser.alumnospracticas_1.portadas.view;

import com.jmarser.alumnospracticas_1.api.models.Album;
import com.jmarser.alumnospracticas_1.api.models.Portada;
import com.jmarser.alumnospracticas_1.api.models.User;

import java.util.ArrayList;

public interface PortadasView {

    void setUsuarios(ArrayList<User> usuarios);

    void setAlbunes(ArrayList<Album> albunes);

    void setPortadas(ArrayList<Portada> portadas);
}
