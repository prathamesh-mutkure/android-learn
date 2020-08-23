package learn.lco.timerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView seconds = findViewById(R.id.seconds);
        final TextView status = findViewById(R.id.status);

        new CountDownTimer(10000, 1000){

            public void onTick(long millisInFuture){
                seconds.setText(String.valueOf((int) millisInFuture/1000));
            }

            public void onFinish(){
                seconds.setText(String.valueOf(0));
                status.setText("DONE!!!");
                status.animate().rotationY(360).setDuration(700);
            }

        }.start();

    }
}
