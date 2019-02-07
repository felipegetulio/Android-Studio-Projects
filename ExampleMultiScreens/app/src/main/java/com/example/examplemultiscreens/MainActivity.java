package com.example.examplemultiscreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent numbersActivityIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

    }

    private void initialize(){
        numbersActivityIntent = new Intent(this, NumbersActivity.class);
    }

    public void openNumbersList(View view){
        startActivity(numbersActivityIntent);
    }

    private View.OnClickListener openActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(numbersActivityIntent);
        }
    };
}
