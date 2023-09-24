package com.jmarser.alumnospracticas_1.usuarios.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.databinding.RowUserBinding;

import java.util.ArrayList;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuariosViewHolder> {

    private ArrayList<User> listaUsuarios;
    OnItemClickListener itemClickListener;

    public UsuariosAdapter(ArrayList<User> listaUsuarios, OnItemClickListener itemClickListener) {
        this.listaUsuarios = listaUsuarios;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public UsuariosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowUserBinding binding = RowUserBinding.inflate(inflater, parent, false);
        return new UsuariosViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosViewHolder holder, int position) {
        holder.bindUser(listaUsuarios.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listaUsuarios.size();
    }


    class UsuariosViewHolder extends RecyclerView.ViewHolder{

        private RowUserBinding binding;

        public UsuariosViewHolder(@NonNull RowUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindUser(User user){
            binding.tvUserName.setText(user.getUsername());
            binding.tvUserEmail.setText(user.getEmail());
            binding.tvUserWebsite.setText(user.getWebsite());

            binding.cardUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClickListener(user);
                }
            });
        }
    }

    // Interface para gestionar el click en los elementos del recyclerView
    public interface OnItemClickListener{
        void onItemClickListener(User user);
    }
}
