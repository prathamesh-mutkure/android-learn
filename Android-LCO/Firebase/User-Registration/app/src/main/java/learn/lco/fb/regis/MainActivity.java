package learn.lco.fb.regis;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference userRef = rootRef.child("user");

    EditText userName, name, email;
    Button button, btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.username);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        button = findViewById(R.id.button);
        btn = findViewById(R.id.btn);

        final HashMap<String, String> userData = new HashMap<>();

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String myUserName = userName.getText().toString().trim();
               String myName = name.getText().toString().trim();
               String myEmail = email.getText().toString().trim();

               userData.put("UserName", myUserName);
               userData.put("Name", myName);
               userData.put("Email", myEmail);

               userRef.push().setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       if (task.isSuccessful()){
                           Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                       } else {
                           Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                       }
                   }
               });

           }
       });

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, SecondActivity.class);
               startActivity(intent);
           }
       });

    }
}
