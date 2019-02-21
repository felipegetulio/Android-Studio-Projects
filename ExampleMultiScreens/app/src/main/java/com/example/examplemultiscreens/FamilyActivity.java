package com.example.examplemultiscreens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private ArrayList<Word> words = new ArrayList<>();

    int [][] matriz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        initialize();
        configureAdapter();
    }

    void configureAdapter(){
        WordAdapater wordAdapater = new WordAdapater(this, words);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordAdapater);
    }

    void initialize(){

        words.add(new Word("father", "әpә"));
        words.add(new Word("mother", "әṭa"));
        words.add(new Word("son", "angsi"));
        words.add(new Word("daughter", "tune"));
        words.add(new Word("older brother", "taachi"));
        words.add(new Word("younger brother", "chalitti"));
        words.add(new Word("older sister", "teṭe"));
        words.add(new Word("younger sister", "kolliti"));
        words.add(new Word("grandmother", "ama"));
        words.add(new Word("grandfather", "paapa"));

    }
}
