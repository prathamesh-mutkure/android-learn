package learn.lco.spanish;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    public void buttonTapped(View view){

        // Getting ID
        int id = view.getId();

        // Getting identifier
        String nameID = view.getResources().getResourceEntryName(id);

        // Getting music file
        int audio = getResources().getIdentifier(nameID, "raw", getPackageName());

        // Playing audio
        mediaPlayer = MediaPlayer.create(this, audio);
        mediaPlayer.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
