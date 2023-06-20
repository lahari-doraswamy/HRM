package com.example.hrm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    private EditText emailEditText;
    private EditText mpinEditText;
    private EditText confirmMpinEditText;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        emailEditText = findViewById(R.id.emailEditText);
        mpinEditText = findViewById(R.id.mpinEditText);
        confirmMpinEditText = findViewById(R.id.confirmMpinEditText);
        signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve entered email, MPIN, and confirm MPIN
                String email = emailEditText.getText().toString();
                String mpin = mpinEditText.getText().toString();
                String confirmMpin = confirmMpinEditText.getText().toString();

                // Validate the entered MPIN and confirm MPIN
                if (mpin.equals(confirmMpin)) {
                    // TODO: Perform signup logic and create a new user

                    // For now, simply display a success message
                    displaySignupSuccess(email);
                } else {
                    // Display an error message if MPIN and confirm MPIN do not match
                    displayError("MPIN and confirm MPIN do not match");
                }
            }
        });
    }

    private void displaySignupSuccess(String email) {
        // Display a success message indicating the signup was successful
        // Replace this with your desired implementation
        String message = "Signup successful for email: " + email;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        // Example: Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void displayError(String errorMessage) {
        Toast.makeText(this, "error message", Toast.LENGTH_SHORT).show();
        // Display an error message
        // Replace this with your desired implementation
        // Example: Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
    }
