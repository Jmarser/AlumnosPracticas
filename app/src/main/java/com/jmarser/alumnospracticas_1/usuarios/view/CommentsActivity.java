package com.jmarser.alumnospracticas_1.usuarios.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.jmarser.alumnospracticas_1.R;
import com.jmarser.alumnospracticas_1.api.models.Comment;
import com.jmarser.alumnospracticas_1.api.models.Post;
import com.jmarser.alumnospracticas_1.databinding.ActivityCommentsBinding;
import com.jmarser.alumnospracticas_1.di.appComponent.AppComponent;
import com.jmarser.alumnospracticas_1.di.appComponent.DaggerAppComponent;
import com.jmarser.alumnospracticas_1.di.appModule.AppModule;
import com.jmarser.alumnospracticas_1.di.appModule.SharedPreferencesModule;
import com.jmarser.alumnospracticas_1.usuarios.adapter.CommentAdapter;
import com.jmarser.alumnospracticas_1.usuarios.presenter.CommentsPresenter;
import com.jmarser.alumnospracticas_1.util.Constantes;
import com.jmarser.alumnospracticas_1.util.ErrorView;

import java.util.ArrayList;

import javax.inject.Inject;

public class CommentsActivity extends AppCompatActivity implements CommentsView, ErrorView, SwipeRefreshLayout.OnRefreshListener {

    private ActivityCommentsBinding binding;
    private Post post;
    private ArrayList<Comment> listadoComentarios;
    private CommentAdapter commentAdapter;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    CommentsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initInjection();
        initListener();

        Bundle bundle = getIntent().getExtras();
        post = bundle.getParcelable(Constantes.BUNDLE_POST);

        if(post != null){
            setFields();
        }

        presenter.getCommentsForPostId(post.getId());
    }

    private void initInjection(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();

        appComponent.inject(this);
    }

    private void initListener(){
        binding.srlComments.setOnRefreshListener(this);
    }

    private void setFields(){
        binding.tvTituloPost.setText(post.getTitle());
        binding.tvBodyPost.setText(post.getBody());
    }

    private void initRecyclerComentarios(){
        if(listadoComentarios != null && listadoComentarios.size() > 0){
            binding.rvCommentsPost.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            commentAdapter = new CommentAdapter(listadoComentarios);
            binding.rvCommentsPost.setAdapter(commentAdapter);
            binding.tvCommentsEmpty.setVisibility(View.GONE);
        }else{
            binding.tvCommentsEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setComments(ArrayList<Comment> listadoComentarios) {
        this.listadoComentarios = listadoComentarios;
        initRecyclerComentarios();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showMessageError(String message) {

    }

    @Override
    public void onRefresh() {
        presenter.getCommentsForPostId(post.getId());
        binding.srlComments.setRefreshing(false);
    }
}