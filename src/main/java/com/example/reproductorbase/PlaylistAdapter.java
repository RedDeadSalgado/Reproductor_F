package com.example.reproductorbase;

import static com.example.reproductorbase.MainActivity.albums;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
// VISTA DE LAS PLAYLISTSSSSSSSSSSSSSSSS
public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.MyFHolder> {
    private Context mContext;
    private ArrayList<MusicFiles> playlistFiles;
    View view;
    public PlaylistAdapter(Context mContext, ArrayList<MusicFiles> playlistFiles){
        this.mContext = mContext;
        this.playlistFiles = playlistFiles;
    }

    @NonNull
    @Override
    public MyFHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.playlist_item,parent,false);
        return new MyFHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFHolder holder,final int position) {
        holder.playlist_name.setText(playlistFiles.get(position).getPlaylist());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PlaylistDetails.class);
                intent.putExtra("playlistName", playlistFiles.get(position).getPlaylist());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return playlistFiles.size();
    }

    public class MyFHolder extends RecyclerView.ViewHolder{
        TextView playlist_name;
        public MyFHolder(@NonNull View itemView){
            super(itemView);
            playlist_name = itemView.findViewById(R.id.playlist_name);
        }
    }
}
