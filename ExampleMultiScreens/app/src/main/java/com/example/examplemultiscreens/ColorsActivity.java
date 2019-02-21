package com.example.examplemultiscreens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private ArrayList<Word> words = new ArrayList<>();

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

        words.add(new Word("red", "weṭeṭṭi"));
        words.add(new Word("green", "chokokki"));
        words.add(new Word("brown", "ṭakaakki"));
        words.add(new Word("gray", "ṭopoppi"));
        words.add(new Word("black", "kululli"));
        words.add(new Word("white", "kelelli"));
        words.add(new Word("dusty yellow", "ṭopiisә"));
        words.add(new Word("mustard yellow", "chiwiiṭә"));
    }
}
