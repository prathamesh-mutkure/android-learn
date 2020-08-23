package learn.lco.snakes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int[] dices = {
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six
    };

    public void buttonTapped(View view){

        ImageView dice = findViewById(R.id.dice);

        // Generating a random number
        Random random = new Random();
        int num = random.nextInt(6);    // 0 - 5

        dice.setImageResource(dices[num]);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
