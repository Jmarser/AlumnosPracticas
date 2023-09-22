package com.jmarser.alumnospracticas_1.portadas.view;

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


public class PortadasFragment extends Fragment implements PortadasView{

    @Inject
    SharedPreferences sharedPreferences;

    public PortadasFragment() {
        // Required empty public constructor
    }

    public static PortadasFragment newInstance() {
        PortadasFragment fragment = new PortadasFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initInjection();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portadas, container, false);
    }

    private void initInjection(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, getContext()))
                .sharedPreferencesModule(new SharedPreferencesModule(getContext()))
                .build();

        appComponent.inject(this);
    }
}