package com.example.reproductorbase;

import static com.example.reproductorbase.MainActivity.musicFiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ArtistDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView artistName;
    String name;
    ImageView backBtn;
    ArrayList<MusicFiles> artistSongs = new ArrayList<>();
    ArtistDetailsAdapter artistDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_details);
        getSupportActionBar().hide();

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();    //finish current activity and go back to previous Activity
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        artistName = findViewById(R.id.nombre_artista);
        name = getIntent().getStringExtra("artisName");
        artistName.setText(name);
        int j = 0;
        for(int i = 0; i < musicFiles.size(); i++){
            if(name.equals(musicFiles.get(i).getArtist())){
                artistSongs.add(j,musicFiles.get(i));
                j++;
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!(artistSongs.size() < 1)){
            artistDetailsAdapter = new ArtistDetailsAdapter(this,artistSongs);
            recyclerView.setAdapter(artistDetailsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        }
    }

    private byte[] getAlbumArt(String uri){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}