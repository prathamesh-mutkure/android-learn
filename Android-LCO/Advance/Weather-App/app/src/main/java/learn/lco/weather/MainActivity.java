package learn.lco.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText input;
    TextView result;

    String baseURL = "http://api.openweathermap.org/data/2.5/forecast?q=";
    String APIID = "&APPID=ab6ac2ded8b4867bab2d7a2487b1e66d";
    String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        input = findViewById(R.id.input);
        result = findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                URL = baseURL + input.getText().toString() + APIID;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

                        Request.Method.GET,
                        URL,
                        null,

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {

                                    // Getting the JSON array named list
                                    JSONArray list = response.getJSONArray("list");

                                    // Getting weather array of 0th index of JSON Array list
                                    String listObject = list.getJSONObject(0).getString("weather");

                                    // Creating array for weather array
                                    JSONArray weatherArray = new JSONArray(listObject);

                                    // Again getting JSON Object at 0th index of weather Array
                                    JSONObject jsonObject = weatherArray.getJSONObject(0);

                                    // Getting the required string out of 0th JSON Object of weather array
                                    String info = jsonObject.getString("main");

                                    // Setting the TextView
                                    result.setText(info);
                                    result.animate().rotationX(360).setDuration(700);

                                } catch (Exception e){
                                    e.printStackTrace();
                                    result.setText(getString(R.string.error));
                                }
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                result.setText(getString(R.string.error));
                            }
                        }
                );

                MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);
            }

        });
    }
}