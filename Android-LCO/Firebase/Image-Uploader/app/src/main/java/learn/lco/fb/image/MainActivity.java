package learn.lco.fb.image;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {

    FirebaseStorage storage;
    StorageReference rootRef;

    Button button;
    ImageView imageView;

    // Request Code
    private final static int GALLERY = 1;

    // Rotating bar
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = FirebaseStorage.getInstance();
        rootRef = storage.getReference();

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);

        // Initializing ProgressDialog
        progressDialog = new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Getting popup for Images
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

//        if (Manifest.permission.READ_EXTERNAL_STORAGE == )

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // On GALLERY REQUEST CODE
        if (requestCode == GALLERY){

            // Message on ProgressDialog
            progressDialog.setMessage("Uploading the Image");

            // Getting the path
            Uri uri = data.getData();

            progressDialog.show();

            // Setting the ImageView
            imageView.setImageURI(uri);

            // Creating the file to database
            StorageReference fileName = rootRef.child("Photos/" + uri.getLastPathSegment() + ".png");

            // Adding the Image to the File
            fileName.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(MainActivity.this, "Upload Successful", Toast.LENGTH_SHORT).show();

                    // Ending the progress bar
                    progressDialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}
