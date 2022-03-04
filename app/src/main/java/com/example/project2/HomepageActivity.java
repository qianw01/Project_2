package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //gets username from login page
        Intent prevInt = getIntent();
        String username = prevInt.getStringExtra("USERNAME");

        DBClass db = new DBClass(getApplicationContext(), "Users");

        TextView welMess = findViewById(R.id.welcomeHome);
        Log.d("log", username + " -----------username");
        welMess.setText("henlo " + db.selectInfoQuery("name", username));

        Button diaryBtn = findViewById(R.id.diaryBtn);
        Button notesBtn = findViewById(R.id.notesBtn);
        Button trackerBtn = findViewById(R.id.trackerBtn);
        Button logoutBtn = findViewById(R.id.logoutBtn);

        //button action handlers
        diaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextInt = new Intent(getApplicationContext(), DailyDiaryActivity.class);
                nextInt.putExtra("USERNAME", username);
                startActivity(nextInt);
            }
        });

        notesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextInt = new Intent(getApplicationContext(), ViewNotesActivity.class);
                nextInt.putExtra("USERNAME", username);
                startActivity(nextInt);
            }
        });

        trackerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextInt = new Intent(getApplicationContext(), MoodActivity.class);
                nextInt.putExtra("USERNAME", username);
                startActivity(nextInt);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextInt = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(nextInt);
            }
        });
    }
}