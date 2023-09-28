package com.jmarser.alumnospracticas_1.albunes.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.jmarser.alumnospracticas_1.R;
import com.jmarser.alumnospracticas_1.albunes.adapters.AlbumAdapter;
import com.jmarser.alumnospracticas_1.albunes.presenter.AlbunesPresenter;
import com.jmarser.alumnospracticas_1.api.models.Album;
import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.databinding.FragmentAlbunesBinding;
import com.jmarser.alumnospracticas_1.di.appComponent.AppComponent;
import com.jmarser.alumnospracticas_1.di.appComponent.DaggerAppComponent;
import com.jmarser.alumnospracticas_1.di.appModule.AppModule;
import com.jmarser.alumnospracticas_1.di.appModule.SharedPreferencesModule;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import java.util.ArrayList;

import javax.inject.Inject;

public class AlbunesFragment extends Fragment implements AlbunesView, ErrorView, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, AdapterView.OnItemSelectedListener {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    AlbunesPresenter albunesPresenter;

    private FragmentAlbunesBinding binding;
    private ArrayList<User> listadoUsuarios;
    private ArrayList<Album> listadoAlbunes;
    private AlbumAdapter albumAdapter;
    private int userId = -1;


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
        binding = FragmentAlbunesBinding.inflate(inflater, container, false);

        initInject();

        initListener();

        albunesPresenter.getAllAlbunes();
        albunesPresenter.getAllUser();

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void initInject(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, getContext()))
                .sharedPreferencesModule(new SharedPreferencesModule(getContext()))
                .build();

        appComponent.inject(this);
    }

    private void initListener(){
        binding.srlAlbunes.setOnRefreshListener(this);
        binding.spnAutores.setOnItemSelectedListener(this);
    }

    private void initRecyclerAlbunes(){
        if(listadoAlbunes != null && listadoAlbunes.size() > 0){
            binding.rvAlbunes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            albumAdapter = new AlbumAdapter(listadoAlbunes);
            binding.rvAlbunes.setAdapter(albumAdapter);
            binding.tvRvAlbumEmpty.setVisibility(View.GONE);
            binding.rvAlbunes.setVisibility(View.VISIBLE);
        }else{
            binding.tvRvAlbumEmpty.setVisibility(View.VISIBLE);
            binding.rvAlbunes.setVisibility(View.INVISIBLE);
        }
    }

    private void initSpinnerUsuarios(){
        String[] nombreUsuarios;
        if(listadoUsuarios != null && listadoUsuarios.size() > 0){
            nombreUsuarios = new String[listadoUsuarios.size() + 1];
            nombreUsuarios[0] = "Todos los usuarios";
            for(int i = 0; i < listadoUsuarios.size(); i++){
                nombreUsuarios[i + 1] = listadoUsuarios.get(i).getUsername();
            }

        }else{
            nombreUsuarios = new String[]{"No hay usuarios disponibles"};
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, nombreUsuarios);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.spnAutores.setAdapter(adapterSpinner);
    }

    @Override
    public void setGetAllUser(ArrayList<User> listadoUsuarios) {
        if(this.listadoUsuarios != null){
            this.listadoUsuarios.clear();
        }
        this.listadoUsuarios = listadoUsuarios;
        initSpinnerUsuarios();
    }

    @Override
    public void setGetAllAlbums(ArrayList<Album> listadoAlbunes) {
        if(this.listadoAlbunes != null){
            this.listadoAlbunes.clear();
        }
        this.listadoAlbunes = listadoAlbunes;
        initRecyclerAlbunes();
    }

    @Override
    public void ErrorServer() {
        binding.layoutError.clError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showMessageError(String message) {

    }

    @Override
    public void onRefresh() {
        if(userId == -1) {
            albunesPresenter.getAllAlbunes();
        }else{
            albunesPresenter.getAlbumsForUser(userId);
        }
        binding.srlAlbunes.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        int idView = v.getId();

        if(idView == binding.layoutError.btnRetry.getId()){
            albunesPresenter.getAllAlbunes();
            albunesPresenter.getAllUser();
            binding.layoutError.clError.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == binding.spnAutores.getId()){
            if(position == 0){
                userId = -1;
                albunesPresenter.getAllAlbunes();
            }else{
                userId = listadoUsuarios.get(position + 1).getId();
                albunesPresenter.getAlbumsForUser(userId);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}