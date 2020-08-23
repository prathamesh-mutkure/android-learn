package learn.lco.maps.staractivityswitcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Array to store color strings
        final ArrayList<String> list = new ArrayList<>();

        // Adding colors
        list.add("red");
        list.add("blue");
        list.add("green");
        list.add("yellow");

        //  ListView
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Going to SecondActivity
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

                // Passing data to SecondActivity
                intent.putExtra("color", list.get(i));

                // Loading SecondActivity
                startActivity(intent);

            }
        });



    }
}
