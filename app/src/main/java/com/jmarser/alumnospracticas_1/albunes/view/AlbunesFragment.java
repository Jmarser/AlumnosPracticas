package com.jmarser.alumnospracticas_1.albunes.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jmarser.alumnospracticas_1.R;
import com.jmarser.alumnospracticas_1.di.appComponent.AppComponent;
import com.jmarser.alumnospracticas_1.di.appComponent.DaggerAppComponent;
import com.jmarser.alumnospracticas_1.di.appModule.AppModule;
import com.jmarser.alumnospracticas_1.di.appModule.SharedPreferencesModule;

import javax.inject.Inject;

public class AlbunesFragment extends Fragment implements AlbunesView{

    @Inject
    SharedPreferences sharedPreferences;

    public AlbunesFragment() {
        // Required empty public constructor
    }

    public static AlbunesFragment newInstance() {
        AlbunesFragment fragment = new AlbunesFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initInject();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albunes, container, false);
    }

    private void initInject(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, getContext()))
                .sharedPreferencesModule(new SharedPreferencesModule(getContext()))
                .build();

        appComponent.inject(this);
    }
}