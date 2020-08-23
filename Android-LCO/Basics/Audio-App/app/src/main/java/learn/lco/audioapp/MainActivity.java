package learn.lco.audioapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    // Plays video
    MediaPlayer mediaPlayer;

    // Managing Audio
    AudioManager audioManager;

    // Volume levels
    int maxVolume, currentVolume;

    public void playTapped(View view){
        mediaPlayer.start();
    }

    public void pauseTapped(View view){
        mediaPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Playing the audio
        mediaPlayer = MediaPlayer.create(this, R.raw.audio);

        // Getting music service
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Getting volume levels
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        // Setting seek bar volume levels
        SeekBar volumeRocker = findViewById(R.id.seekBar);
        volumeRocker.setMax(maxVolume);
        volumeRocker.setProgress(currentVolume);

        volumeRocker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Seek bar of Timeline
        final SeekBar timeline = findViewById(R.id.seekBar2);

        // Setting max duration
        timeline.setMax(mediaPlayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeline.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);

        timeline.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);
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
