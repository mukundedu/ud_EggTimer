package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerText;
    SeekBar timerSeekBar;
    Boolean counterIsActive=false;
    Button goButton;
    CountDownTimer countDownTimer;



    public void resetTimer(){
        timerText.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("Go");
        counterIsActive=false;

    }



    public void goButton(View view) {


        if (counterIsActive) {
           resetTimer();
        }
        else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("STOP!!");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);

                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int secondLeft) {

        ///i is no of seconds
        Log.i("level", Integer.toString(secondLeft));
        int minutes = secondLeft / 60;
        int second = secondLeft - (minutes * 60);

        String secondString = Integer.toString(second);

        ///Giving the zero for the number less than 10
        if(second <=9){
            secondString="0"+secondString;
        }

        /////Setting the timer text
        timerText.setText(Integer.toString(minutes)+ ":" + secondString);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timeSeekBar);
        timerText = (TextView) findViewById(R.id.countDownTextView);
        goButton = findViewById(R.id.goButton);
        timerSeekBar.setProgress(30);
        timerSeekBar.setMax(600);


        ///// Onclick Listener
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

              updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}