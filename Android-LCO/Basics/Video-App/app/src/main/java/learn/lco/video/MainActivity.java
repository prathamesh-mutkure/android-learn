package learn.lco.video;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting the video view
        VideoView videoView = findViewById(R.id.videoView);

        // Setting path
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);

        // Adding Media controls
        MediaController mediaController = new MediaController(this);

        // Linking videoView and mediaController

        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);

        // Playing the video
        videoView.start();
    }
}
