package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button submitBtn = findViewById(R.id.regSubmitBtn);
        TextView userText = findViewById(R.id.usernameReg);
        TextView passText = findViewById(R.id.passReg);

        TextView nameReg = findViewById(R.id.nameReg);
        TextView ageReg = findViewById(R.id.ageReg);
        TextView genderReg = findViewById(R.id.genderReg);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userText.getText().toString();
                String password = passText.getText().toString();
                if (username.length() >= 5 && password.length() >= 8 && Character.isUpperCase(password.charAt(0))) {
                    String name = nameReg.getText().toString();
                    String age = ageReg.getText().toString();
                    String gender = genderReg.getText().toString();
                    DBClass db = new DBClass(getApplicationContext(), "userInfo");
                    db.addUserInfo(name, age, gender, username, password);
                    userText.setText("");
                    passText.setText("");
                    nameReg.setText("");
                    ageReg.setText("");
                    genderReg.setText("");

                    Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(loginIntent);
                }
            }
        });
    }
}