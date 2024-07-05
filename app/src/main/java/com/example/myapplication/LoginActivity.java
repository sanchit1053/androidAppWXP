package com.example.myapplication; // Replace with your package name

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    private TextView textViewWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewWelcome = findViewById(R.id.textViewWelcome);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.equals("admin") && password.equals("admin@123")) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);

            String welcomeMessage = R.string.welcome + username;
            textViewWelcome.setText(welcomeMessage);
            textViewWelcome.setVisibility(View.VISIBLE);
            textViewWelcome.setTextColor(ContextCompat.getColor(this, R.color.colorGreen));
        } else {
            textViewWelcome.setText(R.string.invalid_username);
            textViewWelcome.setTextColor(ContextCompat.getColor(this, R.color.colorRed));
            textViewWelcome.setVisibility(View.VISIBLE);
        }
    }
}
