package learn.lco.volley.image;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String url = "http://pngriver.com/wp-content/uploads/2017/12/download-Android-Technology-logo-PNG-transparent-images-transparent-backgrounds-PNGRIVER-COM-android_logo_PNG4.png";

        final ImageView imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageRequest imageRequest = new ImageRequest(

                        // URL of Image
                        url,

                        // On success
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                imageView.setImageBitmap(response);
                                imageView.animate().rotationY(360).setDuration(600);
                            }
                        },

                        // Dimensions
                        // 0 = Auto
                        0, 0,

                        // Image Scaling
                        ImageView.ScaleType.FIT_START,

                        // Decode Config to null
                        null,

                        // On Error
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("Error", "Error");
                                error.printStackTrace();
                            }
                        }
                );

                MySingleton.getInstance(MainActivity.this).addToRequestQueue(imageRequest);

            }
        });

    }
}
