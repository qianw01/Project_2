package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        TextView loginMess = findViewById(R.id.loginMessage);

        logSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameLog.getText().toString();
                String password = passLog.getText().toString();

                DBClass db = new DBClass(getApplicationContext(), "Users");

                try {
                    String hashedPass = RegisterActivity.generateStrongPasswordHashWithSHA256(password);
                    Log.d("log", db.selectInfoQuery("password", username) + " -----in sql");
                    Log.d("log", hashedPass + " ------inputedd");
                    //if password matches username
                    if (hashedPass.equals(db.selectInfoQuery("password", username))) {
                        Intent nextInt = new Intent(getApplicationContext(), HomepageActivity.class);
                        nextInt.putExtra("USERNAME", username);
                        startActivity(nextInt);
                    } else {
                        loginMess.setText("what happen");
                    }
                //if user doesn't exist
                } catch (Exception e) {
                    loginMess.setText("who");
                }
            }
        });
    }

    //removes back button functionality
    @Override
    public void onBackPressed() { }
}