package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ViewNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        Intent prevInt = getIntent();
        String username = prevInt.getStringExtra("USERNAME");

        DBClass db = new DBClass(getApplicationContext(), "Users");
        String notes = db.selectDiaQuery("note", username);

        TextView notesView = findViewById(R.id.displayNotesView);
        notesView.setText(notes);
        notesView.setMovementMethod(new ScrollingMovementMethod());
    }
}