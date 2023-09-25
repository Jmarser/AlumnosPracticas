package com.jmarser.alumnospracticas_1.usuarios.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jmarser.alumnospracticas_1.R;
import com.jmarser.alumnospracticas_1.api.models.Post;
import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.databinding.ActivityUsuarioDetailBinding;
import com.jmarser.alumnospracticas_1.di.appComponent.AppComponent;
import com.jmarser.alumnospracticas_1.di.appComponent.DaggerAppComponent;
import com.jmarser.alumnospracticas_1.di.appModule.AppModule;
import com.jmarser.alumnospracticas_1.di.appModule.SharedPreferencesModule;
import com.jmarser.alumnospracticas_1.main.MainActivity;
import com.jmarser.alumnospracticas_1.usuarios.adapter.UserDetailsAdapter;
import com.jmarser.alumnospracticas_1.usuarios.presenter.UserDetailsPresenter;
import com.jmarser.alumnospracticas_1.util.Constantes;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import java.util.ArrayList;

import javax.inject.Inject;

public class UsuarioDetailActivity extends AppCompatActivity implements UsuarioDetailsView, ErrorView, UserDetailsAdapter.OnItemPostClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ActivityUsuarioDetailBinding binding;
    private User user;
    private ArrayList<Post> listadoPosts;
    private UserDetailsAdapter userDetailsAdapter;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    UserDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsuarioDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initInjection();
        initListeners();

        Bundle bundle = getIntent().getExtras();
        user = bundle.getParcelable(Constantes.BUNDLE_USUARIO);

        if(user != null){
            setFields();
        }

        presenter.getPostsForUser();

    }

    private void initInjection(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();

        appComponent.inject(this);
    }

    private void initListeners(){
        binding.srlUserDetails.setOnRefreshListener(this);
    }

    private void setFields(){
        binding.tvNombre.setText(user.getName());
        binding.tvNombreUsuario.setText(user.getUsername());
    }

    private void initRecyclerPost(){
        if(listadoPosts != null && listadoPosts.size() > 0){
            userDetailsAdapter = new UserDetailsAdapter(listadoPosts, this);
            userDetailsAdapter.filtrarPostsForUser(user);
            if(userDetailsAdapter.getItemCount() > 0){
                binding.rvDetailsUser.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                binding.rvDetailsUser.setAdapter(userDetailsAdapter);
                binding.tvDetailsUserEmpty.setVisibility(View.GONE);
            }else{
                binding.tvDetailsUserEmpty.setVisibility(View.VISIBLE);
            }
        }else{
            binding.tvDetailsUserEmpty.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void setPostsForUser(ArrayList<Post> listadoPosts) {
        this.listadoPosts = listadoPosts;
        initRecyclerPost();
    }

    @Override
    public void showError() {
        binding.tvDetailsUserEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessageError(String message) {

    }

    @Override
    public void onItemPostClickListener(Post post) {
        Toast.makeText(this, "Pulsado post con id: " + post.getId(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        presenter.getPostsForUser();
        binding.srlUserDetails.setRefreshing(false);
    }
}