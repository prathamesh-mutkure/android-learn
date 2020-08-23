package learn.lco.listviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Array of all US states
        String[] states = {"California,", "Alabama,", "Arkansas,", "Arizona,", "Alaska,",
                "Colorado,", "Connecticut,", "Delaware,", "Florida,", "Georgia,",
                "Hawaii,", "Idaho,", "Illinois,", "Indiana,", "Iowa,", "Kansas,",
                "Kentucky,", "Louisiana,", "Maine,", "Maryland,", "Massachusetts,",
                "Michigan,", "Minnesota,", "Mississippi,", "Missouri,", "Montana,",
                "Nebraska,", "Nevada,", "New Hampshire,", "New Jersey,", "New Mexico,",
                "New York,", "North Carolina,", "North Dakota,", "Ohio,", "Oklahoma,", "Oregon,",
                "Pennsylvania,", "Rhoda Island,", "South Carolina,", "South Dakota,", "Tennessee,",
                "Texas,", "Utah,", "Vermont,", "Virginia,", "Washington,", "West Virginia,", "Wisconsin,",
                "Wyoming" };

        // Converting array to a List
        final ArrayList<String> stateList = new ArrayList<>(Arrays.asList(states));

        // Adapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, stateList);

        // Getting the ListView
        ListView listView = findViewById(R.id.listView);

        // Setting the adapter to list view
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "You tapped on " + stateList.get(i), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
