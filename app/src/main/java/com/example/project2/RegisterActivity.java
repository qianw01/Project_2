package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button submitBtn = findViewById(R.id.regSubmitBtn);
        TextView userText = findViewById(R.id.usernameReg);
        TextView passText = findViewById(R.id.passReg);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userText.getText().toString();
                String password = passText.getText().toString();
                if (username.length() >= 5 && password.length() >= 8 && Character.isUpperCase(password.charAt(0))) {

                }
            }
        });
    }
}