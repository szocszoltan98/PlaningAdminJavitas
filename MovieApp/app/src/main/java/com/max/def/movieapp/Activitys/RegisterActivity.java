package com.max.def.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText name, email, password, confirmpassword;
    Button register;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        register = findViewById(R.id.register);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametext, emailtext, passwordtext, confirmpasswordtext;
                nametext = name.getText().toString().trim();
                emailtext = email.getText().toString().trim();
                passwordtext = password.getText().toString().trim();
                confirmpasswordtext = confirmpassword.getText().toString().trim();
                if (check(nametext, emailtext, passwordtext, confirmpasswordtext)) {
                    addUser();
                    Toast.makeText(getBaseContext(), "Registration was succesful!", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });

    }

    private void addUser() {

        String useremail = email.getText().toString().trim();
        String userpassword = password.getText().toString().trim();
        final String username = name.getText().toString().trim();
        if (!TextUtils.isEmpty(username)) {

            String id = databaseUsers.push().getKey();
            User user = new User(useremail, userpassword);
            databaseUsers.child(username).setValue(user);
            Toast.makeText(this, "User added", Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }


    boolean check(String nametext, String emailtext, String passwordtext, String confirmpasswordtext) {
        if (nametext.isEmpty()) {
            Toast.makeText(getBaseContext(), "Name field is empty!", Toast.LENGTH_LONG).show();
            return false;
        } else if (emailtext.isEmpty()) {
            Toast.makeText(getBaseContext(), "Email field is empty!", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (passwordtext.isEmpty()) {
                Toast.makeText(getBaseContext(), "Password field is empty!", Toast.LENGTH_LONG).show();
                return false;
            } else if (confirmpasswordtext.isEmpty()) {
                Toast.makeText(getBaseContext(), "Confirmpassword field is empty!", Toast.LENGTH_LONG).show();
                return false;
            } else {
                if (!passwordtext.equals(confirmpasswordtext)) {
                    {
                        Toast.makeText(getBaseContext(), "Passwords not equals!", Toast.LENGTH_LONG).show();
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }
    }

}
