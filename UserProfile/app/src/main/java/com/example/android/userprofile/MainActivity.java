package com.example.android.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView name;
    TextView birthday;
    TextView country;
    ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        birthday = findViewById(R.id.birthday);
        country = findViewById(R.id.country);
        profilePicture = findViewById(R.id.profile_picture);


        name.setText("Felipe");
        birthday.setText("24 de dezembro de 1996");
        country.setText("Brazil");
        profilePicture.setImageResource(R.drawable.boy);
    }
}
