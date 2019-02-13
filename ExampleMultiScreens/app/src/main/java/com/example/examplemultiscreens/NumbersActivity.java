package com.example.examplemultiscreens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class NumbersActivity extends AppCompatActivity {

    private String [] words = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);


        for(int i=0; i<words.length; ++i){
            Log.v("NumbersActivity", "word stored in index " + String.valueOf(i) + ": " + words[i]);
        }

    }


    void initialize(){

    }
}
