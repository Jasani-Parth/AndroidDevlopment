package com.example.studyplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class event_select extends AppCompatActivity {

    private Button study ;
    private Button assignment ;
    private Button exam ;
    private Button lecture ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_select);

        this.study = (Button) findViewById(R.id.event_select_study) ;
        this.assignment = (Button) findViewById(R.id.event_select_assignment) ;
        this.exam = (Button) findViewById(R.id.event_select_exam) ;
        this.lecture = (Button) findViewById(R.id.event_select_lecture) ;

        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(event_select.this, "study", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(event_select.this,Add_Event_Study.class) ;
                startActivity(intent);
            }
        });

        assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(event_select.this,"assignment",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(event_select.this,Add_Event_Assignment.class) ;
                startActivity(intent);
            }
        });

        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(event_select.this,"exam",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(event_select.this,Add_Event_Exam.class) ;
                startActivity(intent);
            }
        });

        lecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(event_select.this,"lecture",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(event_select.this,Add_Event_Lecture.class) ;
                startActivity(intent);
            }
        });

    }
}