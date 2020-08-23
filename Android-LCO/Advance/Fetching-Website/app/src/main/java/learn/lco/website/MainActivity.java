package learn.lco.website;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class websiteFetcher extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            URL url;
            String page = "";
            HttpURLConnection urlConnection;

            try {

                // Getting the URL
                url = new URL(strings[0]);

                // setting up the HTTP connection
                urlConnection = (HttpURLConnection) url.openConnection();

                // Getting the Input Stream
                InputStream inputStream = urlConnection.getInputStream();

                // Reading from Input Stream
                InputStreamReader reader = new InputStreamReader(inputStream);

                // Reading individual character in form of an integer
                int data = reader.read();

                // -1 marks the end of file
                while (data != -1){

                    char readData = (char) data;
                    page += readData;
                    data = reader.read();
                }

                return page;

            } catch (Exception e){
                Log.i("Error", "Error fetching website!");
                e.printStackTrace();
                return "Could not fetch the webpage!";
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);

        String page;

        websiteFetcher website = new websiteFetcher();

        try {
            page = website.execute("http://hiteshchoudhary.com").get();
            textView.setText(page);
        } catch (Exception e) {
            e.printStackTrace();
            textView.setText("Unable to Load the page!");
        }

    }
}
