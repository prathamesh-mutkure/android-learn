package learn.lco.volley.one;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button and TextView\
        Button button = findViewById(R.id.button);
        final TextView textView = findViewById(R.id.textView);

        // URL
        final String url = "http://hiteshchoudhary.com/";

        // When button is tapped
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Needs some context
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                // String request
                // ARGUMENT 1 = Method
                // ARGUMENT 2 = URL
                // ARGUMENT 3 = Action on success
                // ARGUMENT 4 = Action on failure/error
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        url,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                textView.setText(response);
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                textView.setText("Unable to load data\n\n" + String.valueOf(error));
                            }
                        }
                );

                requestQueue.add(stringRequest);

            }
        });

    }
}
