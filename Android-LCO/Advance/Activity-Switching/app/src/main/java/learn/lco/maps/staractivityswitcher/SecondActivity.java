package learn.lco.maps.staractivityswitcher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private static int getImageID(String imageName, Context context){
        return (context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView = findViewById(R.id.textView);
        ImageView imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button);

        // Getting Data from MainActivity
        Intent intent = getIntent();
        String color = intent.getStringExtra("color");

        Toast.makeText(this, color, Toast.LENGTH_SHORT).show();

        // Setting Image
        imageView.setImageResource(getImageID(color, getApplicationContext()));
        imageView.animate().rotationY(360).setDuration(700);
        textView.setText(color);

        // Going Back
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(goBack);
            }
        });

    }
}
