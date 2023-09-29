package com.jmarser.alumnospracticas_1.portadas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jmarser.alumnospracticas_1.api.models.Portada;
import com.jmarser.alumnospracticas_1.databinding.RowPortadaBinding;

import java.util.ArrayList;

public class PortadasAdapter extends RecyclerView.Adapter<PortadasAdapter.PortadaViewHolder> {

    private ArrayList<Portada> portadas;

    public PortadasAdapter(ArrayList<Portada> portadas) {
        this.portadas = portadas;
    }

    @NonNull
    @Override
    public PortadaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowPortadaBinding binding = RowPortadaBinding.inflate(inflater, parent, false);
        return new PortadaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PortadaViewHolder holder, int position) {
        holder.bindPortada(portadas.get(position));
    }

    @Override
    public int getItemCount() {
        return portadas.size();
    }

    class PortadaViewHolder extends RecyclerView.ViewHolder{

        private RowPortadaBinding binding;

        public PortadaViewHolder(@NonNull RowPortadaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindPortada(Portada portada){
            Glide.with(binding.getRoot().getContext()).load(portada.getThumbnailUrl()).into(binding.imgPortada);
            binding.tvIdAlbum.setText("Nº: " + portada.getId());
        }
    }
}
