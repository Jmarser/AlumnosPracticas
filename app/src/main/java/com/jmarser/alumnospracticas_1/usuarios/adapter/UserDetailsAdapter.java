package com.jmarser.alumnospracticas_1.usuarios.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmarser.alumnospracticas_1.api.models.Post;
import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.databinding.RowDetailsUserBinding;

import java.util.ArrayList;

public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.PostsViewHolder>{

    private ArrayList<Post> listadoPosts;
    private ArrayList<Post> listadoPostsFiltrados;
    OnItemPostClickListener itemPostClickListener;

    public UserDetailsAdapter(ArrayList<Post> listadoPosts, OnItemPostClickListener itemPostClickListener) {
        this.listadoPosts = listadoPosts;
        this.listadoPostsFiltrados = new ArrayList<>(listadoPosts);
        this.itemPostClickListener = itemPostClickListener;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowDetailsUserBinding binding = RowDetailsUserBinding.inflate(inflater, parent, false);
        return new PostsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        holder.bindPost(listadoPostsFiltrados.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listadoPostsFiltrados.size();
    }


    public void filtrarPostsForUser(User user){
        listadoPostsFiltrados.clear();

        for(Post post: listadoPosts){
            if(post.getUserId() == user.getId()){
                listadoPostsFiltrados.add(post);
            }
        }
    }

    class PostsViewHolder extends RecyclerView.ViewHolder{

        private RowDetailsUserBinding binding;

        public PostsViewHolder(@NonNull RowDetailsUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindPost(Post post){
            binding.tvTitlePost.setText(post.getTitle());

            binding.cvDetailsUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemPostClickListener.onItemPostClickListener(post);
                }
            });
        }
    }

    public interface OnItemPostClickListener{
        void onItemPostClickListener(Post post);
    }
}
