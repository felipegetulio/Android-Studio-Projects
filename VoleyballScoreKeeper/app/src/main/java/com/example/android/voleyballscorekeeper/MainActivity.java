package com.example.android.voleyballscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int WINNING_SCORE_IN_SET = 25;

    int scoreCountTeamA;
    int scoreCountTeamB;
    int setCountTeamA;
    int setCountTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize(){
        scoreCountTeamA = scoreCountTeamB = 0;
        setCountTeamA = setCountTeamB = 0;
        displayScore(R.id.team_a_score, scoreCountTeamA);
        displayScore(R.id.team_b_score, scoreCountTeamB);
        displaySetCount(R.id.set_count_team_a, setCountTeamA);
        displaySetCount(R.id.set_count_team_b, setCountTeamB);
    }

    public void addPointToTeamA(View view){
        ++scoreCountTeamA;

        if(scoreCountTeamA >= WINNING_SCORE_IN_SET  && Math.abs(scoreCountTeamA - scoreCountTeamB) > 1){
            scoreCountTeamA = scoreCountTeamB = 0;
            displaySetCount(R.id.set_count_team_a, ++setCountTeamA);
            displayScore(R.id.team_b_score, scoreCountTeamB);
        }

        displayScore(R.id.team_a_score, scoreCountTeamA);
    }

    public void addPointToTeamB(View view){
        ++scoreCountTeamB;

        if(scoreCountTeamB >= WINNING_SCORE_IN_SET  && Math.abs(scoreCountTeamA - scoreCountTeamB) > 1){
            scoreCountTeamA = scoreCountTeamB = 0;
            displaySetCount(R.id.set_count_team_b, ++setCountTeamB);
            displayScore(R.id.team_a_score, scoreCountTeamA);
        }

        displayScore(R.id.team_b_score, scoreCountTeamB);
    }

    public void displayScore(int idTeam, int score) {
        TextView textView = findViewById(idTeam);
        textView.setText(String.valueOf(score));
    }

    public void displaySetCount(int idTeam, int setCount){
        TextView textView = findViewById(idTeam);
        textView.setText(String.valueOf(setCount));
    }

    public void reset(View view){
        initialize();
    }
}
