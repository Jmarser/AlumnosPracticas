package com.jmarser.alumnospracticas_1.portadas.interactor;

import com.jmarser.alumnospracticas_1.api.models.Album;
import com.jmarser.alumnospracticas_1.api.models.Portada;
import com.jmarser.alumnospracticas_1.api.models.User;

import java.util.ArrayList;

public interface PortadasInteractor {

    void getAllUsers(OnGetAllUsersCallBack callBack);

    interface OnGetAllUsersCallBack{
        void onSuccessGetAllUsers(ArrayList<User> usuarios);
        void onErrorGetAllUsers();
    }

    void getAllAlbunes(OnGetAllAlbunesCallBack callBack);

    void getAlbunesForUser(int userId, OnGetAllAlbunesCallBack callBack);

    interface OnGetAllAlbunesCallBack{
        void onSuccessGetAlbunes(ArrayList<Album> albunes);
        void onErrorGetAlbunes();
    }

    void getAllPortadas(OnGetPortadasCallBack callBack);

    void getPortadasForAlbum(int albumId, OnGetPortadasCallBack callBack);

    interface OnGetPortadasCallBack{
        void onSuccessGetPortadas(ArrayList<Portada> portadas);
        void onErrorGetPortadas();
    }
}
