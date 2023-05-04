package com.example.firebaseauthass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    EditText usernameDoc;
    EditText emailLoginD;
    EditText passLoginD;
    EditText adress;
    Button loginDOC;
    TextView donthaveAccountDOC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

    usernameDoc = findViewById(R.id.usernameDoc);
    emailLoginD = findViewById(R.id.emailLoginD);
    passLoginD = findViewById(R.id.passLoginD);
    adress =findViewById(R.id.adress);
    loginDOC =findViewById(R.id.loginDOC);


        ///////////////////////////////
        donthaveAccountDOC =findViewById(R.id.donthaveAccountDOC);
    firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    progressDialog = new ProgressDialog(this);

    donthaveAccountDOC.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         startActivity(new Intent(Signup.this,MainActivity.class));
     }
 });

 loginDOC.setOnClickListener(view ->{
     createUser();
 });


    }
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference usersCollection = db.collection("users");

    public void createUser() {
        String username = usernameDoc.getText().toString();
        String email = emailLoginD.getText().toString();
        String password = passLoginD.getText().toString();
        String address = adress.getText().toString();

        if (TextUtils.isEmpty(email)){
            emailLoginD.setError("Email cannot be empty");
            emailLoginD.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            passLoginD.setError("Password cannot be empty");
            passLoginD.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            usernameDoc.setError("Username cannot be empty");
            usernameDoc.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            adress.setError("Your Address cannot be empty");
            adress.requestFocus();

        }else {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Map<String, Object> userData = new HashMap<>();
                        userData.put("username", usernameDoc);
                        userData.put("email", emailLoginD);
                        userData.put("password", passLoginD);
                        userData.put("address", adress);
                        usersCollection.add(userData)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        // Data added successfully
                                        Toast.makeText(Signup.this, "Data added", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Data addition failed
                                        Toast.makeText(Signup.this, "Data addition failed", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        Toast.makeText(Signup.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = task.getResult().getUser();
                        String userId = user.getUid();
                        FirebaseFirestore db = FirebaseFirestore.getInstance();



                        userData.put("email", email);
                        userData.put("password", password);
                        db.collection("user").document(userId).set(userData)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {

                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        startActivity(new Intent(Signup.this, MainActivity.class));
                                        finish();
                                    }

                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Signup failed
                                        Toast.makeText(Signup.this, "Signup failed", Toast.LENGTH_SHORT).show();
                                    }
                                });


                    } else {
                        Toast.makeText(Signup.this, "Signup failed", Toast.LENGTH_SHORT).show();
                    }


                }
            });


        }
    }
}