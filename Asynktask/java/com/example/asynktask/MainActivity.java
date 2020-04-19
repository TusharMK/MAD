package com.example.asynktask;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    ProgressBar progressBar;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
    }
//buttonclicked :-
    public void startPlaying(View view) {
        new MyClass().execute();

    }

    class MyClass extends AsyncTask<Integer, Integer, String> {

        public MyClass() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = findViewById(R.id.progressBar);
            progressBar.setProgress(0);


            Toast.makeText(MainActivity.this, "Music Started", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, "Music Stopped", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
           // seekBar.setProgress(values[0]);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            player = MediaPlayer.create(getApplicationContext(), Settings.System.DEFAULT_RINGTONE_URI);
            player.start();
            progressBar.setMax(player.getDuration());

            seekBar.setMax(player.getDuration());
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    player.seekTo(progress);
                    progressBar.setProgress(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Toast.makeText(MainActivity.this, "Music Stopped", Toast.LENGTH_SHORT).show();
                }
            });
            int prpr = 0;
            while (player.getDuration() != player.getCurrentPosition()) {

                int crpr = player.getCurrentPosition();

                if(crpr - prpr > 200) {
                  //  Toast.makeText(MainActivity.this, "Pr : " + crpr, Toast.LENGTH_SHORT).show();
                    publishProgress(crpr);
                    prpr = crpr;
                }
            }

            return null;
        }

    }
}
