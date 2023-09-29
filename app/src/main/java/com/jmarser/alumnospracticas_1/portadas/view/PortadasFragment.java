package com.jmarser.alumnospracticas_1.portadas.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.jmarser.alumnospracticas_1.R;
import com.jmarser.alumnospracticas_1.api.models.Album;
import com.jmarser.alumnospracticas_1.api.models.Portada;
import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.databinding.FragmentPortadasBinding;
import com.jmarser.alumnospracticas_1.di.appComponent.AppComponent;
import com.jmarser.alumnospracticas_1.di.appComponent.DaggerAppComponent;
import com.jmarser.alumnospracticas_1.di.appModule.AppModule;
import com.jmarser.alumnospracticas_1.di.appModule.SharedPreferencesModule;
import com.jmarser.alumnospracticas_1.portadas.adapters.PortadasAdapter;
import com.jmarser.alumnospracticas_1.portadas.presenter.PortadasPresenter;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import java.util.ArrayList;

import javax.inject.Inject;


public class PortadasFragment extends Fragment implements PortadasView, ErrorView, AdapterView.OnItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    PortadasPresenter portadasPresenter;

    private FragmentPortadasBinding binding;
    private ArrayList<User> usuarios;
    private ArrayList<Album> albunes;
    private ArrayList<Portada> portadas;
    private PortadasAdapter portadasAdapter;
    private int userId = -1;
    private int albumId = -1;

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
        binding = FragmentPortadasBinding.inflate(inflater, container, false);

        initInjection();

        initListener();

        binding.srlPortadas.setRefreshing(true);
        portadasPresenter.getAllPortadas();
        portadasPresenter.getAllUsers();
        portadasPresenter.getAllAlbunes();

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void initInjection(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, getContext()))
                .sharedPreferencesModule(new SharedPreferencesModule(getContext()))
                .build();

        appComponent.inject(this);
    }

    private void initListener(){
        binding.spnAutoresPortadas.setOnItemSelectedListener(this);
        binding.spnAlbunesPortadas.setOnItemSelectedListener(this);
        binding.srlPortadas.setOnRefreshListener(this);
    }

    private void initRecyclerPortadas(){
        if(portadas != null && portadas.size() > 0){
            binding.rvPortadas.setLayoutManager(new GridLayoutManager(getContext(), 2));
            portadasAdapter = new PortadasAdapter(portadas);
            binding.rvPortadas.setAdapter(portadasAdapter);
            binding.tvRvPortadasEmpty.setVisibility(View.GONE);
            binding.srlPortadas.setRefreshing(false);
        }else{
            binding.tvRvPortadasEmpty.setVisibility(View.VISIBLE);
        }
    }

    private void initSpinnerUsuarios(){
        String[] nombreUsuarios;
        if(usuarios != null && usuarios.size() > 0){
            nombreUsuarios = new String[usuarios.size() + 1];
            nombreUsuarios[0] = "Todos los usuarios";
            for(int i = 0; i < usuarios.size(); i++){
                nombreUsuarios[i + 1] = usuarios.get(i).getUsername();
            }
        }else{
            nombreUsuarios = new String[]{"No hay usuarios disponibles"};
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, nombreUsuarios);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.spnAutoresPortadas.setAdapter(adapterSpinner);
    }

    private void initSpinnerAlbunes(){
        String[] nombreAlbunes;
        if(albunes != null && albunes.size() > 0){
            nombreAlbunes = new String[albunes.size() + 1];
            nombreAlbunes[0] = "Todos los albunes";
            for(int i = 0; i < albunes.size(); i++){
                nombreAlbunes[i + 1] = albunes.get(i).getTitle();
            }
        }else{
            nombreAlbunes = new String[]{"No hay albunes disponibles"};
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, nombreAlbunes);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.spnAlbunesPortadas.setAdapter(adapterSpinner);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int idView = parent.getId();
        if (idView == binding.spnAutoresPortadas.getId()){
            if(position == 0){
                userId = -1;
                portadasPresenter.getAllAlbunes();
            }else{
                userId = usuarios.get(position).getId();
                portadasPresenter.getAlbunesForUser(userId);
            }
        }

        if (idView == binding.spnAlbunesPortadas.getId()){
            if(position == 0){
                albumId = -1;
                portadasPresenter.getAllPortadas();
            }else{
                albumId = albunes.get(position).getId();
                portadasPresenter.getPortadasForAlbum(albumId);
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void setUsuarios(ArrayList<User> usuarios) {
        this.usuarios = usuarios;
        initSpinnerUsuarios();
    }

    @Override
    public void setAlbunes(ArrayList<Album> albunes) {
        if (this.albunes != null){
            this.albunes.clear();
        }
        this.albunes = albunes;
        initSpinnerAlbunes();
    }

    @Override
    public void setPortadas(ArrayList<Portada> portadas) {
        if (this.portadas != null){
            this.portadas.clear();
        }
        this.portadas = portadas;
        initRecyclerPortadas();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showMessageError(String message) {

    }

    @Override
    public void onRefresh() {
        if(albumId != -1){
            portadasPresenter.getPortadasForAlbum(albumId);
        }else{
            portadasPresenter.getAllPortadas();
        }
        binding.srlPortadas.setRefreshing(false);
    }
}