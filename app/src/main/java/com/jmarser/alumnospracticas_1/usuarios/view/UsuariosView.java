package com.jmarser.alumnospracticas_1.usuarios.view;

import com.jmarser.alumnospracticas_1.api.models.User;

import java.util.ArrayList;

public interface UsuariosView {

    void setUsers(ArrayList<User> listadoUsuarios);

    void errorServer();
}
