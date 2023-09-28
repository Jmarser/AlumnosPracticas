package com.jmarser.alumnospracticas_1.albunes.interactor;

import com.jmarser.alumnospracticas_1.api.models.Album;
import com.jmarser.alumnospracticas_1.api.models.User;

import java.util.ArrayList;

public interface AlbunesInteractor {

    void getAllUsers(OnGetAllUsersCallback callBack, OnErrorServer errorServer);

    interface OnGetAllUsersCallback{
        void onSuccessGetAllUsers(ArrayList<User> listadoUsuarios);
        void onErrorGetAllUsers();
    }

    void getAllAlbunes(OnGetAllAlbunesCallback callBack, OnErrorServer errorServer);

    interface OnGetAllAlbunesCallback{
        void onSuccessGetAllAlbunes(ArrayList<Album> listadoAlbunes);
        void onErrorGetAllAlbunes();
    }

    void getAlbunesForUser(int userId, OnGetAllAlbunesCallback callBack, OnErrorServer errorServer);

    interface OnErrorServer{
        void onErrorServer();
    }
}
