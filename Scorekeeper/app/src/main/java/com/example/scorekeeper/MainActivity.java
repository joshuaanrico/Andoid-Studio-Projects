package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int score1, score2;
    TextView scoreText1, scoreText2;
    static final String STATE_SCORE_1 = "Team 1 Score",
    STATE_SCORE_2 = "Team 2 Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText1 = (TextView) findViewById(R.id.team1);
        scoreText2 = (TextView) findViewById(R.id.team2);

        if (savedInstanceState != null){
            score1 = savedInstanceState.getInt(STATE_SCORE_1);
            score2 = savedInstanceState.getInt(STATE_SCORE_2);
            scoreText1.setText(String.valueOf(score1));
            scoreText2.setText(String.valueOf(score2));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate){
        outstate.putInt(STATE_SCORE_1, score1);
        outstate.putInt(STATE_SCORE_2, score2);
        super.onSaveInstanceState(outstate);
    }

    public void increaseScore(View view) {
        int viewID = view.getId();

        switch (viewID){
            case R.id.plus1:
                score1++;
                scoreText1.setText(String.valueOf(score1));
                break;
            case R.id.plus2:
                score2++;
                scoreText2.setText(String.valueOf(score2));
                break;
        }
    }

    public void decreaseScore(View view) {
        int viewID = view.getId();

        switch (viewID){
            case R.id.minus1:
                score1--;
                scoreText1.setText(String.valueOf(score1));
                break;
            case R.id.minus2:
                score2--;
                scoreText2.setText(String.valueOf(score2));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        }else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        if (item.getItemId() == R.id.night_mode){
            int nightMode = AppCompatDelegate.getDefaultNightMode();

            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }
        return true;
    }
}
