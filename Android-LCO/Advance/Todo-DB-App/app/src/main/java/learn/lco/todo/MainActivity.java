package learn.lco.todo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ListView listView;
    ArrayList<String> taskList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        dbHelper = new DBHelper(this);

        loadAllTask();
    }

    public void deleteTapped(View view){

        try {

            int index = listView.getPositionForView(view);
            String item = taskList.get(index);

            dbHelper.removeTask(item);
            loadAllTask();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadAllTask(){
        taskList = dbHelper.getTaskList();

        if (adapter == null){
            adapter = new ArrayAdapter<>(this, R.layout.custom_list_view, R.id.task_title, taskList);
            listView.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(taskList);
            adapter.notifyDataSetChanged();
        }

    }

    // Adding Icon to menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case (R.id.addTask):
                final EditText editText = new EditText(this);

                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add new Tasks")
                        .setMessage("Whats your task")
                        .setView(editText)
                        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String task = String.valueOf(editText.getText());
                                dbHelper.addTask(task);

                                loadAllTask();
                            }
                        })
                        .setNegativeButton("CANCEL", null)
                        .create();

                dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
