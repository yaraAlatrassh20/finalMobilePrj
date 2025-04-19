package com.example.finalmobileproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Patterns;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton;
    private CheckBox rememberPassword;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView signUpText;

    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String REMEMBER_ME = "REMEMBER_ME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        setupViews();
        loadSavedData();
    }

    private void setupViews() {
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        rememberPassword = findViewById(R.id.rememberPassword);
        signUpText = findViewById(R.id.signUpText);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadSavedData() {
        String savedEmail = sharedPreferences.getString(EMAIL, "");
        String savedPassword = sharedPreferences.getString(PASSWORD, "");

        emailInput.setText(savedEmail);
        passwordInput.setText(savedPassword);

        if (!savedEmail.isEmpty() && !savedPassword.isEmpty()) {
            rememberPassword.setChecked(true);
        }
    }

    public void btnLoginOnClick(View view) {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(LoginActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rememberPassword.isChecked()) {
            editor.putString(EMAIL, email);
            editor.putString(PASSWORD, password);
            editor.putBoolean(REMEMBER_ME, true);
            editor.commit();
        } else {
            editor.remove(EMAIL);
            editor.remove(PASSWORD);
            editor.putBoolean(REMEMBER_ME, false);
            editor.commit();
        }
    }
}
