package com.example.finalmobileproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {


    private EditText edtEmail, edtUsername, edtPass;
    private Button btnSignUp;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String USERNAME = "USERNAME";

    private void setupViews() {
        edtEmail = findViewById(R.id.email);
        edtUsername = findViewById(R.id.username);
        edtPass = findViewById(R.id.password);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        setupViews();
    }
}
