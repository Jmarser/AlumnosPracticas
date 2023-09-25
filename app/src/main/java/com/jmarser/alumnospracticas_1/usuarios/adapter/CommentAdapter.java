package com.jmarser.alumnospracticas_1.usuarios.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmarser.alumnospracticas_1.api.models.Comment;
import com.jmarser.alumnospracticas_1.databinding.RowCommentBinding;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private ArrayList<Comment> listadoCoemntarios;

    public CommentAdapter(ArrayList<Comment> listadoCoemntarios) {
        this.listadoCoemntarios = listadoCoemntarios;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RowCommentBinding binding = RowCommentBinding.inflate(layoutInflater, parent, false);
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.bindComment(listadoCoemntarios.get(position));
    }

    @Override
    public int getItemCount() {
        return listadoCoemntarios.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder{

        private RowCommentBinding binding;

        public CommentViewHolder(@NonNull RowCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindComment(Comment comment){
            binding.tvEmailComment.setText(comment.getEmail());
            binding.tvNameComment.setText(comment.getName());
            binding.tvBodyComment.setText(comment.getBody());
        }
    }
}
