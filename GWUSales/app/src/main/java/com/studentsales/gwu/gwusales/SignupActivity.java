package com.studentsales.gwu.gwusales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    Button signup;
    Button login;
    EditText first;
    EditText last;
    EditText username;
    EditText password;
    EditText phonenumber;
    String firsttxt;
    String lasttxt;
    String usernametxt;
    String passwordtxt;
    String phonenumbertxt;
    private static final String VALID_EMAIL_ADDRESS_REGEX =
            "([A-Za-z0-9])\\w+@gwu.edu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup = (Button)findViewById(R.id.buttonSignup);
        login = (Button)findViewById(R.id.buttonLoginReg);
        first = (EditText)findViewById(R.id.editTextFirstName);
        last = (EditText)findViewById(R.id.editTextLastName);
        username = (EditText)findViewById(R.id.editTextRegisterEmail);
        password = (EditText)findViewById(R.id.editTextPswrd);
        phonenumber = (EditText)findViewById(R.id.editTextPhone);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firsttxt = first.getText().toString();
                lasttxt = last.getText().toString();
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                phonenumbertxt = phonenumber.getText().toString();
                Boolean b = usernametxt.matches(VALID_EMAIL_ADDRESS_REGEX);


                if (usernametxt.equals("") && passwordtxt.equals("") && firsttxt.equals("")
                        && lasttxt.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please complete the sign up form", Toast.LENGTH_SHORT).show();
                } else if (b) {
                        Log.d("test", "email is valid");

                        ParseUser user = new ParseUser();
                        user.setUsername(usernametxt);
                        user.setPassword(passwordtxt);
                        user.setEmail(usernametxt);
                        user.put("firstName", firsttxt);
                        user.put("lastName", lasttxt);
                        user.put("mobileNumber", phonenumbertxt);
                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Sign up error", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                    else {
                        Log.d("test", "email is invalid");
                        Toast.makeText(getApplicationContext(), "Email has to be gwu.edu", Toast.LENGTH_LONG).show();
                    }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        SignupActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
