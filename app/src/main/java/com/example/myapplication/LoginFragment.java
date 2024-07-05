package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {

    private static final String[] USERS = {"user1", "user2", "user3", "user4", "user5", "user6"};
    private static final String[] PASSWORDS = {"password1", "password2", "password3", "password4", "password5", "password6"};

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    private TextView textViewWelcome;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        buttonLogin = view.findViewById(R.id.buttonLogin);
        textViewWelcome = view.findViewById(R.id.textViewWelcome);

        // Set content descriptions for accessibility
        editTextUsername.setContentDescription(getString(R.string.username_edittext_cd));
        editTextPassword.setContentDescription(getString(R.string.password_edittext_cd));
        buttonLogin.setContentDescription(getString(R.string.login_button_cd));
        textViewWelcome.setContentDescription(getString(R.string.welcome_textview_cd));

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        return view;
    }

    private void login() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Check if username and password match
        boolean isValidUser = false;
        for (int i = 0; i < USERS.length; i++) {
            if (USERS[i].equals(username) && PASSWORDS[i].equals(password)) {
                isValidUser = true;
                break;
            }
        }

        if (isValidUser) {
            if(getActivity() instanceof MainActivity){
                ((MainActivity) getActivity()).successfulLogin();
            }
            showWelcomeMessage(username);
        } else {
            showError();
        }
    }

    private void showWelcomeMessage(String username) {
        // Set welcome message
        textViewWelcome.setText("Welcome, " + username + "!");
        textViewWelcome.setTextColor(Color.GREEN); // Set text color to green
        textViewWelcome.setVisibility(View.VISIBLE);

        // Clear input fields
        editTextUsername.setText("");
        editTextPassword.setText("");

        // Provide accessibility feedback
        textViewWelcome.announceForAccessibility("Login successful. Welcome, " + username);

        // Set focus and ensure visibility
        editTextUsername.requestFocus();
    }

    private void showError() {
        // Show error message
        Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT).show();

        // Provide accessibility feedback
        textViewWelcome.announceForAccessibility("Invalid username or password. Please try again.");

        // Set focus and ensure visibility
        editTextUsername.requestFocus();
    }
}
