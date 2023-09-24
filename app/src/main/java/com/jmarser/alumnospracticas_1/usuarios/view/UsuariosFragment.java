package com.jmarser.alumnospracticas_1.usuarios.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jmarser.alumnospracticas_1.R;
import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.databinding.FragmentUsuariosBinding;
import com.jmarser.alumnospracticas_1.di.appComponent.AppComponent;
import com.jmarser.alumnospracticas_1.di.appComponent.DaggerAppComponent;
import com.jmarser.alumnospracticas_1.di.appModule.AppModule;
import com.jmarser.alumnospracticas_1.di.appModule.SharedPreferencesModule;
import com.jmarser.alumnospracticas_1.usuarios.adapter.UsuariosAdapter;
import com.jmarser.alumnospracticas_1.usuarios.presenter.UsuariosPresenter;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import java.util.ArrayList;

import javax.inject.Inject;


public class UsuariosFragment extends Fragment implements UsuariosView, ErrorView, UsuariosAdapter.OnItemClickListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private FragmentUsuariosBinding binding;
    private UsuariosAdapter usuariosAdapter;
    private ArrayList<User> listadoUsuarios;

    @Inject
    SharedPreferences preferences;

    @Inject
    UsuariosPresenter presenter;

    public UsuariosFragment() {
        // Required empty public constructor
    }
    
    public static UsuariosFragment newInstance() {
        UsuariosFragment fragment = new UsuariosFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUsuariosBinding.inflate(inflater, container, false);

        initInjection();
        initListener();

        presenter.getUsers();

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
        binding.layoutError.btnRetry.setOnClickListener(this);
        binding.pullRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onItemClickListener(User user) {
        Toast.makeText(getContext(), user.getUsername(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        int idView = v.getId();

        if(idView == binding.layoutError.btnRetry.getId()){
            presenter.getUsers();
        }
    }

    @Override
    public void setUsers(ArrayList<User> listadoUsuarios) {
        this.listadoUsuarios = listadoUsuarios;
        initRecycler();
    }

    private void initRecycler(){
        if(listadoUsuarios != null && listadoUsuarios.size() > 0){
            binding.rvUsers.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            usuariosAdapter = new UsuariosAdapter(listadoUsuarios, this);
            binding.rvUsers.setAdapter(usuariosAdapter);
        }else{
            binding.tvRecyclerEmpty.setVisibility(View.VISIBLE);
            binding.rvUsers.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {
        initRecycler();
    }

    @Override
    public void showMessageError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        initRecycler();
    }

    @Override
    public void errorServer() {
        binding.layoutError.clError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        presenter.getUsers();
        binding.pullRefresh.setRefreshing(false);
    }
}