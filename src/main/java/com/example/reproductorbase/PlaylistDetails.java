package com.example.reproductorbase;

import static com.example.reproductorbase.MainActivity.musicFiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class PlaylistDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    String playlistName;
    ImageView backBtn;
    ArrayList<MusicFiles> playlistSongs = new ArrayList<>();
    PlaylistDetailsAdapter playlistDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_details);
        getSupportActionBar().hide();

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();    //finish current activity and go back to previous Activity
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        playlistName = getIntent().getStringExtra("playlistName");
        int j = 0;
        for(int i = 0; i < musicFiles.size(); i++){
            if(playlistName.equals(musicFiles.get(i).getPlaylist())){
                playlistSongs.add(j,musicFiles.get(i));
                j++;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!(playlistSongs.size()<1)){
            playlistDetailsAdapter = new PlaylistDetailsAdapter(this,playlistSongs);
            recyclerView.setAdapter(playlistDetailsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        }
    }
}