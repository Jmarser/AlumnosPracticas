package com.jmarser.alumnospracticas_1.usuarios.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.jmarser.alumnospracticas_1.api.models.Post;
import com.jmarser.alumnospracticas_1.api.models.Task;
import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.databinding.ActivityUsuarioDetailBinding;
import com.jmarser.alumnospracticas_1.di.appComponent.AppComponent;
import com.jmarser.alumnospracticas_1.di.appComponent.DaggerAppComponent;
import com.jmarser.alumnospracticas_1.di.appModule.AppModule;
import com.jmarser.alumnospracticas_1.di.appModule.SharedPreferencesModule;
import com.jmarser.alumnospracticas_1.usuarios.adapter.TasksAdapter;
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
    private ArrayList<Task> listadoTodos;
    private TasksAdapter tasksAdapter;
    private boolean isSwipeRefresh1 = false;

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
        //initListenersSwipeRefreshLayout();

        Bundle bundle = getIntent().getExtras();
        user = bundle.getParcelable(Constantes.BUNDLE_USUARIO);

        if(user != null){
            setFields();
        }

        presenter.getPosts(user.getId());
        presenter.getTodos();

        listenerSwipeRefresPosts();
        listenerSwipeRefreshTasks();

    }

    private void initInjection(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();

        appComponent.inject(this);
    }

    private void initListenersSwipeRefreshLayout(){
        binding.srlUserDetails.setOnRefreshListener(this);
        binding.srlUserTodos.setOnRefreshListener(this);
    }

    private void setFields(){
        binding.tvNombre.setText(user.getName());
        binding.tvNombreUsuario.setText(user.getUsername());
    }

    private void listenerSwipeRefresPosts(){
        binding.srlUserDetails.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getPosts(user.getId());
                binding.srlUserDetails.setRefreshing(false);
            }
        });
    }

    private void listenerSwipeRefreshTasks(){
        binding.srlUserTodos.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getTodos();
                binding.srlUserTodos.setRefreshing(false);
            }
        });
    }

    private void initRecyclerPost(){
        if(listadoPosts != null && listadoPosts.size() > 0){
            userDetailsAdapter = new UserDetailsAdapter(listadoPosts, this);
            //userDetailsAdapter.filtrarPostsForUser(user);
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

    private void initRecyclerTodos(){
        if(listadoTodos != null && listadoTodos.size() > 0){
            tasksAdapter = new TasksAdapter(listadoTodos);
            tasksAdapter.filtrarTasksForUser(user);
            if(tasksAdapter.getItemCount() > 0){
                binding.rvTodosUser.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                binding.rvTodosUser.setAdapter(tasksAdapter);
                binding.tvTodosEmpty.setVisibility(View.GONE);
            }else{
                binding.tvTodosEmpty.setVisibility(View.VISIBLE);
            }
        }else{
            binding.tvTodosEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setPosts(ArrayList<Post> listadoPosts) {
        this.listadoPosts = listadoPosts;
        initRecyclerPost();
    }

    @Override
    public void setTodos(ArrayList<Task> listadoTodos) {
        this.listadoTodos = listadoTodos;
        initRecyclerTodos();
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
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constantes.BUNDLE_POST, post);

        Intent intent = new Intent(this, CommentsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Modo para controlar, implementando la interface SwipeRefreshLayout.OnRefreshListener en la declaración de la activity, cual de los diferentes swiprefreshlayout
     * ha sigo pulsado para ejecutar el código necesario.
     * */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            if(ev.getRawY() >= binding.srlUserDetails.getY() && ev.getRawY() <= binding.srlUserDetails.getY() + binding.srlUserDetails.getHeight()){
                isSwipeRefresh1 = true;
            }else if(ev.getRawY() >= binding.srlUserTodos.getY() && ev.getRawY() <= binding.srlUserTodos.getY() + binding.srlUserTodos.getHeight()){
                isSwipeRefresh1 = false;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onRefresh() {

        if(isSwipeRefresh1){
            Toast.makeText(this, "PullRefresh de posts", Toast.LENGTH_LONG).show();
            presenter.getPosts(user.getId());
            binding.srlUserDetails.setRefreshing(false);
        }else{
            Toast.makeText(this, "PullRefresh de Tasks", Toast.LENGTH_LONG).show();
            presenter.getTodos();
            binding.srlUserTodos.setRefreshing(false);
        }
        isSwipeRefresh1 = false;
    }
}