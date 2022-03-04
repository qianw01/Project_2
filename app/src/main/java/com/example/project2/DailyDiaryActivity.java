package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DailyDiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_diary);

        Intent prevInt = getIntent();
        String username = prevInt.getStringExtra("USERNAME");

        TextView enterNotes = findViewById(R.id.diaryTextInput);

        CheckBox chk1 = findViewById(R.id.checkBox);
        CheckBox chk2 = findViewById(R.id.checkBox2);
        CheckBox chk3 = findViewById(R.id.checkBox3);
        CheckBox chk4 = findViewById(R.id.checkBox4);
        CheckBox chk5 = findViewById(R.id.checkBox5);

        Button diarySubBtn = findViewById(R.id.diarySubmitBtn);

        diarySubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBClass db = new DBClass(getApplicationContext(), "Users");

                String notes = enterNotes.getText().toString();
                int checkedBoxes = (chk1.isChecked()? 1 : 0) + (chk2.isChecked()? 10 : 0) + (chk3.isChecked()? 100 : 0) + (chk4.isChecked()? 1000 : 0) + (chk5.isChecked()? 10000 : 0);

                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String stringDate = dateFormat.format(date);

                db.addDiaryInfo(username, stringDate, notes, checkedBoxes);

                Intent nextInt = new Intent(getApplicationContext(), HomepageActivity.class);
                nextInt.putExtra("USERNAME", username);
                Log.d("log", username + " -----------username from diary");
                startActivity(nextInt);
            }
        });
    }
}