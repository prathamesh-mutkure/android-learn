package learn.lco.bulbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void blueTapped(View view) {

        ImageView blue = findViewById(R.id.blueBulb);
        ImageView green = findViewById(R.id.greenBulb);

        blue.animate().alpha(1).setDuration(750);
        green.animate().alpha(0).setDuration(750);
    }

    public void greenTapped(View view){

        ImageView blue = findViewById(R.id.blueBulb);
        ImageView green = findViewById(R.id.greenBulb);

        blue.animate().alpha(0).setDuration(750);
        green.animate().alpha(1).setDuration(750);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
