package learn.lco.grid.musicapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    public void buttonTapped(View view){

        // Unique ID of each element
        // Getting ID of tapped button
        int id = view.getId();

        // Identifier of that element
        // Identifier of tapped button
        String nameID = view.getResources().getResourceEntryName(id);

        // Getting the music file
       int myMusic = getResources().getIdentifier(nameID, "raw", getPackageName());

       mediaPlayer = MediaPlayer.create(this, myMusic);
       mediaPlayer.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
