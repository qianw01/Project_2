package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button logSubmitBtn = findViewById(R.id.logSubmitBtn);
        TextView usernameLog = findViewById(R.id.usernameLog);
        TextView passLog = findViewById(R.id.passLog);

        logSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameLog.getText().toString();
                String password = passLog.getText().toString();

                if (username.selectInfoQuery(username)) {

                }
                Intent nextInt = new Intent(getApplicationContext(), HomepageActivity.class);
                startActivity(nextInt);
            }
        });
    }
}