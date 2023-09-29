package com.jmarser.alumnospracticas_1.portadas.presenter;

public interface PortadasPresenter {

    void getAllUsers();

    void getAllAlbunes();

    void getAllPortadas();

    void getAlbunesForUser(int userId);

    void getPortadasForAlbum(int albumId);

}
