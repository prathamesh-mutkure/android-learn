package learn.lco.buttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void signInTapped(View view){
        Log.i("Sign In", "Sign In button tapped!");
    }

    public void signUpTapped(View view){
        Log.i("Sign Up", "Sign Up button tapped!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
