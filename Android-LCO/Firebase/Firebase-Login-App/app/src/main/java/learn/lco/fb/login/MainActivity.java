package learn.lco.fb.login;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText myEmail;
    EditText myPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        // Checks if the user is already signed-in
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null)
            Toast.makeText(this, "Already Signed-in", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myEmail = findViewById(R.id.email);
        myPassword = findViewById(R.id.password);

        // Initializing Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    public void regisTapped(View view) {

        final String email = myEmail.getText().toString();
        final String password = myPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(MainActivity.this, "Enter all details", Toast.LENGTH_SHORT).show();
        } else {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                // If sign-in is successful
                                Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign-in fails
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }

    public void loginTapped(View view){

        final String email = myEmail.getText().toString();
        final String password = myPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // If sign-in is successful
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign-in fails
                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void logoutTapped(View view){
        mAuth.signOut();
        Toast.makeText(MainActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
    }

}
