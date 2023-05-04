package com.example.firebaseauthass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class profilepickimg extends AppCompatActivity {

   private ImageView ProfileImageView;
   private Button closeButton,saveButton;
   private TextView profileChangeBtn;

   private DatabaseReference databaseReference;
   private FirebaseAuth mAuth;
   private Uri imageUri;
   private String myuri ="";
   private StorageTask uploadTask;
   private StorageReference storageProfilePicRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepickimg);
/*
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        storageProfilePicRef = FirebaseStorage.getInstance().getReference().child("profile pic");
        ProfileImageView = findViewById(R.id.ProfileImageView);
        closeButton = findViewById(R.id.closeButton);
        saveButton = findViewById(R.id.saveButton);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profilepickimg.this, ProfilePage.class));
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadprofileImage();
            }


            private void uploadprofileImage() {

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("set your profile");
                progressDialog.setMessage("please wait ,while we are setting your data");
                progressDialog.show();

                if (imageUri != null) {
                    final StorageReference fileRef = storageProfilePicRef.child(mAuth.getCurrentUser().getUid() + ".jpg");
                    uploadTask = fileRef.getFile(imageUri);
                    uploadTask.continueWithTask(new Continuation() {
                        @Override
                        public Object then(@NonNull Task task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return null;
                        }
                    });
                }
            }
        });
*/
    }



}