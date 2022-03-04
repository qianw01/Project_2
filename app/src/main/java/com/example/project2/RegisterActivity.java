package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

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

                String age = ageReg.getText().toString();
                //checks to see if age is a number
                boolean ageIsNum = android.text.TextUtils.isDigitsOnly(age);

                //checks to ensure that age is a number and username and password fit the requirements
                if (ageIsNum && username.length() >= 5 && password.length() >= 8 && Character.isUpperCase(password.charAt(0))) {
                    //gets all the inputted information and puts it into the database
                    String name = nameReg.getText().toString();
                    String gender = genderReg.getText().toString();

                    DBClass db = new DBClass(getApplicationContext(), "Users");
                    String hashedPass = generateStrongPasswordHashWithSHA256(password);
                    Log.d("log", hashedPass + " ------reiged");
                    db.addUserInfo(name, age, gender, username, hashedPass);

                    //resets all the fields to nothing
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

    //hash function
    public static String generateStrongPasswordHashWithSHA256(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.reset();
            sha256.update(password.getBytes());
            byte[] hash = sha256.digest();

            return toHex(hash);
        } catch (Exception e) {
            System.out.println("Exception: Error in generating password"
                    + e.toString());
        }
        return "";
    }

    private static String toHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}