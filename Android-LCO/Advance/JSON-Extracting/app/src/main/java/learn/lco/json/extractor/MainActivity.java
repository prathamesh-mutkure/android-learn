package learn.lco.json.extractor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String url = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";

    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        final ListView listView = findViewById(R.id.listView);

        list.add("NESTED JSON OBJECTS: ");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JsonObjectRequest request = new JsonObjectRequest(

                        Request.Method.GET,
                        url,
                        null,

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                // NESTED JSON:

                                try {

                                    String coordinate = response.getString("coord");
                                    list.add(coordinate);

                                    JSONObject nested_json = new JSONObject(coordinate);

                                    String lon = nested_json.getString("lon");
                                    String lat = nested_json.getString("lat");

                                    list.add("Longitude: " + lon);
                                    list.add("Latitude: " + lat);

                                } catch (Exception e){
                                    e.printStackTrace();
                                }


                                // JSON ARRAY:

                                try {

                                    list.add("JSON ARRAYS: ");

                                    String weather = response.getString("weather");
                                    list.add(weather);

                                    JSONArray jsonArray = new JSONArray(weather);

                                    for (int i=0; i<jsonArray.length(); i++){
                                        JSONObject parObj = jsonArray.getJSONObject(i);

                                        list.add("ID: " + parObj.getString("id"));
                                        list.add("Main: " + parObj.getString("main"));
                                        list.add("Description: " + parObj.getString("description"));
                                        list.add("Icon: " + parObj.getString("icon"));
                                    }

                                } catch (Exception e){
                                    e.printStackTrace();
                                }

                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                Log.i("Error", String.valueOf(error));
                                list.add("ERROR!");
                            }
                        }
                );

                MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
                listView.setAdapter(arrayAdapter);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "You tapped on: " + list.get(i), Toast.LENGTH_SHORT).show();
            }
        });

    }
}