package com.max.def.movieapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText name, password;
    Button login;
    FirebaseDatabase databaseUsers;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.edt_Name);
        password = findViewById(R.id.edt_Password);
        login = (Button) findViewById(R.id.btn_Login);
        ref = FirebaseDatabase.getInstance().getReference().child("users");


    }

    String pin;
    String userID;

    public void btnLogin(View view) {
        String nametext = name.getText().toString().trim();
        ;
        String passwordtext = password.getText().toString().trim();
        ;
        if (!check(nametext, passwordtext)) {
            userID = name.getText().toString();
            pin = password.getText().toString();
            if (ref.child(userID) != null) {
                ref.child(userID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String name = ds.getValue(String.class);
                            if (userID != name) {
                                if (pin.equals(user.getPassword())) {
                                    Toast.makeText(getBaseContext(), "Login successful!", Toast.LENGTH_LONG).show();
                                    Intent start = new Intent(LoginActivity.this, MoviesActivity.class);
                                    startActivity(start);
                                } else {
                                    Toast.makeText(getBaseContext(), "Enter the correct pin...!", Toast.LENGTH_LONG).show();
                                }

                            } else {

                                Toast.makeText(LoginActivity.this, "This user didn't exist", Toast.LENGTH_SHORT).show();


                            }


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            } else {
                Toast.makeText(getBaseContext(), "User doesn't exist!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

    }

    boolean check(String emailtext, String passwordtext) {

        if (emailtext.isEmpty()) {
            Toast.makeText(getBaseContext(), "Name field is empty!", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (passwordtext.isEmpty()) {
                Toast.makeText(getBaseContext(), "Password field is empty!", Toast.LENGTH_LONG).show();
                return false;
            }
            return false;

        }
    }
}
