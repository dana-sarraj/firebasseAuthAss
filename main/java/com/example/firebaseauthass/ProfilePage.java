package com.example.firebaseauthass;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.Any;

import java.util.Map;

public class ProfilePage extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseAuth;
    private FirebaseAuth Auth;
    ImageView profilepic;
    String username;
    TextView emailadress ,usernameid ,adress;
    private Map<String , String> userMap;
    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    String uid;
    private static final String USERS = "Guest";
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

     /*  uid=firebaseAuth.getUid().toString();
       Log.e(TAG, uid);
       username= intent.getStringExtra("username");*/

          Intent intent = getIntent();
          DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
          DatabaseReference userRef = rootRef.child(USERS);
          Log.v("USERID", userRef.getKey());
            profilepic =findViewById(R.id.profilepic);
            usernameid = findViewById(R.id.usernameid);
            emailadress= findViewById(R.id.emailadress);
            adress =findViewById(R.id.adress);
              profilepic.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      startActivity(new Intent(ProfilePage.this,pickpic.class));
                  }
              });

            userRef.addValueEventListener(new ValueEventListener() {
                String username , email , address ;
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot keyId : dataSnapshot.getChildren()) {
                        if (keyId.child("email").getValue().equals(email)) {
                            username = keyId.child("username").getValue(String.class);
                            email = keyId.child("email").getValue(String.class);
                            address = keyId.child("adress").getValue(String.class);
                            break;
                        }
                    }
                    usernameid.setText(username);
                    emailadress.setText(email);
                    adress.setText(address);

                }
                @Override
                public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                }
            });




      }
}