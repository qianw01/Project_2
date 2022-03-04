package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        Intent prevInt = getIntent();
        String username = prevInt.getStringExtra("USERNAME");

        Spinner spinner = (Spinner) findViewById(R.id.moodSpinner);
        String[] spinnerOptions = new String[] {"neutral", "sad", "very sad", "happy", "very happy"};
        // Create an ArrayAdapter using a default spinner layout and spinnerOptions
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerOptions);
        spinner.setAdapter(adapter);

        RatingBar anxBar = findViewById(R.id.anxietyBar);

        RadioGroup radioMed = findViewById(R.id.radioMed);
        RadioGroup radioTimeMed = findViewById(R.id.radioTimeMed);

        Button submitBtn = findViewById(R.id.trackSubBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get teh values of the input fields
                int mood = spinner.getSelectedItemPosition();
                int anx = anxBar.getNumStars();

                int radioMedSel = radioMed.getCheckedRadioButtonId();
                int radioMedIdx = radioMed.indexOfChild(radioMed.findViewById(radioMedSel));

                int radioTimeMedSel = radioTimeMed.getCheckedRadioButtonId();
                int radioTimeMedIdx = radioTimeMed.indexOfChild(radioTimeMed.findViewById(radioTimeMedSel));

                //default value of 3 means that the user is not on meds
                int meds = 2;

                //if the user has taken meds
                if (radioMedIdx == 0) {
                    //if the user has taken meds on time
                    if (radioTimeMedIdx == 0) {
                        meds = 0;
                    } else {
                        meds = 1;
                    }
                }

                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String stringDate = dateFormat.format(date);

                DBClass db = new DBClass(getApplicationContext(), "Users");
                db.addTrackerInfo(username, stringDate, mood, anx, meds);

                Intent nextInt = new Intent(getApplicationContext(), HomepageActivity.class);
                nextInt.putExtra("USERNAME", username);
                startActivity(nextInt);
            }
        });
    }
}