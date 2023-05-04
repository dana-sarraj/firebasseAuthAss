package com.example.firebaseauthass;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    TextView donthaveAccountDOC;
    Button loginDOC;
    EditText emailLoginD;
    EditText passLoginD;
    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        donthaveAccountDOC = findViewById(R.id.donthaveAccountDOC);
        loginDOC = findViewById(R.id.loginDOC);
        emailLoginD = findViewById(R.id.emailLoginD);
        passLoginD = findViewById(R.id.passLoginD);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        emailLoginD.setText(username);
        passLoginD.setText(password);

        loginDOC.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ProfilePage.class));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("myCh", "my channel", NotificationManager.IMPORTANCE_DEFAULT);

                    NotificationManager manager = getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder = new Notification.Builder(this,"myCh")
                        .setSmallIcon(android.R.drawable.stat_notify_sync)
                        .setContentTitle("first notification")
                        .setContentText("welcome Guest");
                notification = builder.build();
               // notificationManagerCompat = NotificationManagerCompat.from(this);
                  notificationManagerCompat = NotificationManagerCompat.from(this);
            }

            public void push(View v) {
                notificationManagerCompat.notify(1, notification);

            }

        });

        donthaveAccountDOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Signup.class));
            }
        });

        loginDOC.setOnClickListener(view ->{
            loginUser();
        });

        }


    private void loginUser() {
        String email = emailLoginD.getText().toString();
        String Password = passLoginD.getText().toString();

        if (TextUtils.isEmpty(email)) {
            emailLoginD.setError("Email cannot be empty");
            emailLoginD.requestFocus();
        } else if (TextUtils.isEmpty(Password)) {
            passLoginD.setError("Password cannot be empty");
            passLoginD.requestFocus();
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {


                        Toast.makeText(MainActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, ProfilePage.class));
                    } else {
                        Toast.makeText(MainActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
