package com.example.reproductorbase;

import static com.example.reproductorbase.MainActivity.musicFiles;
import static com.example.reproductorbase.MainActivity.playlists;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlaylistFragment extends Fragment {

    RecyclerView recyclerView;
    static PlaylistAdapter playlistAdapter;
    public PlaylistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        if(!(playlists.size() < 1)){
            playlistAdapter = new PlaylistAdapter(getContext(), playlists);
            recyclerView.setAdapter(playlistAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        }
        return view;
    }
}