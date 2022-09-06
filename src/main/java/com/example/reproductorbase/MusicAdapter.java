package com.example.reproductorbase;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyVieHolder> {

    private Context mContext;
    static ArrayList<MusicFiles> mFiles;

    MusicAdapter(Context mContext, ArrayList<MusicFiles> mFiles){
        this.mFiles = mFiles;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.music_items, parent, false);
        return new MyVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.MyVieHolder holder,final int position) {
        holder.file_name.setText(mFiles.get(position).getTittle());
        byte[] image = getAlbumArt(mFiles.get(position).getPath());
        if(image != null){
            Glide.with(mContext).asBitmap().load(image).into(holder.album_art);
        }
        else{
            Glide.with(mContext).load(R.drawable.noalbum).into(holder.album_art);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PlayerActivity.class);
                intent.putExtra("position",position);
                mContext.startActivity(intent);
            }
        });
        holder.menuMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext,v);
                popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                popupMenu.show();
                Menu menuOpts = popupMenu.getMenu();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.add_playlist:
                                if(mFiles.get(position).getPlaylist().equals("Favorites")) {
                                    //menuOpts.getItem(1).setTitle("Eliminar");
                                    Toast.makeText(mContext, "Eliminada de favoritos", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(mContext, "Agregada a favoritos", Toast.LENGTH_SHORT).show();
                                }
                                agregarPlaylist(position,v);
                                break;
                            case R.id.info:
                                Toast.makeText(mContext, "Informacion", Toast.LENGTH_SHORT).show();
                                mostrarInfo();
                                break;
                        }
                        return true;
                    }
                });
            }
        });
    }

    private void agregarPlaylist(int position, View v){
        if(mFiles.get(position).getPlaylist().equals("Favorites")) {
            mFiles.get(position).setPlaylist("0");
        }
        else{
            mFiles.get(position).setPlaylist("Favorites");
        }
    }

    private void mostrarInfo(){

    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }


    public class MyVieHolder extends RecyclerView.ViewHolder{
        TextView file_name;
        ImageView album_art,menuMore;
        public MyVieHolder(@NonNull View itemView){
            super(itemView);
            file_name = itemView.findViewById(R.id.music_file_name);
            album_art = itemView.findViewById(R.id.music_img);
            menuMore = itemView.findViewById(R.id.menuMore);
        }
    }

    private byte[] getAlbumArt(String uri){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }

    void updateList(ArrayList<MusicFiles> musicFilesArrayList){
        mFiles = new ArrayList<>();
        mFiles.addAll(musicFilesArrayList);
        notifyDataSetChanged();
    }


}
