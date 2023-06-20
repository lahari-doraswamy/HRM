package com.example.hrm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText employeeIdEditText;
        EditText mpinEditText;
        Button loginButton;
        TextView signupTextView;
            employeeIdEditText = findViewById(R.id.employeeIdEditText);
            mpinEditText = findViewById(R.id.mpin);
            loginButton = findViewById(R.id.loginButton);
            signupTextView = findViewById(R.id.signupTextView);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Retrieve entered employee ID and MPIN
                    String employeeId = employeeIdEditText.getText().toString();
                    String mpin = mpinEditText.getText().toString();

                    // TODO: Perform login validation and authentication

                    // For now, simply display the entered values
                    displayLoginInfo(employeeId, mpin);
                }
            });

            signupTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the signup activity
                    Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                    startActivity(intent);
                }
            });
        }

        private void displayLoginInfo (String employeeId, String mpin){
            // Display the entered employee ID and MPIN in a Toast or another UI element
            // Replace this with your desired implementation
            String message = "Employee ID: " + employeeId + "\nMPIN: " + mpin;
            // Example: Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
