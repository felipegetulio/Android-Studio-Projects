package com.example.musicapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.song_list)
    private ListView songListView;

    private ArrayList<SongInfo> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initialize();
    }

    private void initialize(){
        songList = new ArrayList<>();
        searchSongsInDevice();
    }


    private void searchSongsInDevice(){

        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        int idIndex, titleIndex, artistIndex, albumIndex;
        String titleValue, artistValue, albumValue;
        long idValue;

        if(musicCursor!=null && musicCursor.moveToFirst()){

            idIndex = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            titleIndex = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            artistIndex = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            albumIndex = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);

            do {
                idValue = musicCursor.getLong(idIndex);
                titleValue = musicCursor.getString(titleIndex);
                artistValue = musicCursor.getString(artistIndex);
                albumValue = musicCursor.getString(albumIndex);

                songList.add(new SongInfo(idValue, titleValue, artistValue, albumValue));
            }while (musicCursor.moveToNext());
        }

    }
}
