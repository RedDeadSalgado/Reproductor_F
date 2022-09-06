package com.example.reproductorbase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.MyViHolder> {
    private Context mContext;
    private ArrayList<MusicFiles> artistFiles;
    View view;
    public ArtistAdapter(Context mContext,ArrayList<MusicFiles> artistFiles){
        this.mContext = mContext;
        this.artistFiles = artistFiles;
    }

    @NonNull
    @Override
    public MyViHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.artist_item,parent,false);
        return new MyViHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViHolder holder,final int position) {
        holder.artist_name.setText(artistFiles.get(position).getArtist());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ArtistDetails.class);
                intent.putExtra("artisName",artistFiles.get(position).getArtist());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artistFiles.size();
    }

    public class MyViHolder extends RecyclerView.ViewHolder{
        TextView artist_name;
        public MyViHolder(@NonNull View itemView){
            super(itemView);
            artist_name = itemView.findViewById(R.id.artist_name);
        }
    }

    void updateList(ArrayList<MusicFiles> musicFilesArrayList){
        artistFiles = new ArrayList<>();
        artistFiles.addAll(musicFilesArrayList);
        notifyDataSetChanged();
    }
}
