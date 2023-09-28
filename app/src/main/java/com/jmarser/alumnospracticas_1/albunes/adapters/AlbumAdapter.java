package com.jmarser.alumnospracticas_1.albunes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmarser.alumnospracticas_1.api.models.Album;
import com.jmarser.alumnospracticas_1.databinding.RowAlbumBinding;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private ArrayList<Album> listadoAlbunes;

    public AlbumAdapter(ArrayList<Album> listadoAlbunes) {
        this.listadoAlbunes = listadoAlbunes;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RowAlbumBinding binding = RowAlbumBinding.inflate(layoutInflater, parent, false);
        return new AlbumViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.bindAlbum(listadoAlbunes.get(position));
    }

    @Override
    public int getItemCount() {
        return listadoAlbunes.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder{

        private RowAlbumBinding binding;

        public AlbumViewHolder(@NonNull RowAlbumBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindAlbum(Album album){
            binding.tvTitleAlbum.setText(album.getTitle());
        }
    }
}
