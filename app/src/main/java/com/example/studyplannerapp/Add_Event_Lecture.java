package com.example.studyplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class Add_Event_Lecture extends AppCompatActivity {

    private DatePickerDialog datePickerDialog ;
    private Button date_picker_button ;
    private Button time_picker_button ;
    private EditText lecSubject ;
    private EditText lecDuration ;
    private EditText lecProf ;
    int hour,minute ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_lecture);

        initDatePicker();
        this.date_picker_button = (Button) findViewById(R.id.date_picker_button) ;
        this.date_picker_button.setText(getToday());

        this.time_picker_button = (Button) findViewById(R.id.time_picker_button) ;

        this.lecSubject = (EditText) findViewById(R.id.assignment_subject) ;
        this.lecDuration = (EditText) findViewById(R.id.assignment_description) ;
        this.lecProf = (EditText) findViewById(R.id.exam_duration) ;

        Button save_event = (Button) findViewById(R.id.save_event);

        save_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lsubject = lecSubject.getText().toString() ;
                String ldur = lecDuration.getText().toString() ;
                int lduration = 0;
                if (ldur.length()!=0){
                    lduration = Integer.parseInt(ldur) ;
                }
                String lprof = lecProf.getText().toString() ;
                String date = date_picker_button.getText().toString() ;
                String time = time_picker_button.getText().toString() ;

                if ( lsubject.length() != 0 && ldur.length() != 0 && lprof.length() != 0 && !time.equals("Event Time")) {
                    // Database
                    DBHandlerLecture dbHandlerLecture = new DBHandlerLecture(Add_Event_Lecture.this,"DataBaseLectureEvent",null,1) ;
                    dbHandlerLecture.addLectureEvent(new EventLecture(lduration,lsubject,lprof,date,time));
                    dbHandlerLecture.close();
                    Toast.makeText(Add_Event_Lecture.this, "EVENT ADDED.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Add_Event_Lecture.this, "ADD DETAILS PROPERLY!!", Toast.LENGTH_SHORT).show();
                }

                finish(); // This Will Stop This Activity..
                Intent intent = new Intent(Add_Event_Lecture.this,MainActivity.class) ;
                startActivity(intent);
            }
        });
    }

    private String getToday() {
        Calendar calendar = Calendar.getInstance() ;
        int year = calendar.get(Calendar.YEAR) ;
        int month = calendar.get(Calendar.MONTH) ;
        month = month + 1 ;
        int day = calendar.get(Calendar.DAY_OF_MONTH) ;

        return makeDateString(year,month,day) ;
    }

    private String makeDateString(int year, int month, int dayOfMonth) {
        return  getmonth(month)+ "  " + dayOfMonth + "  " + year ;
    }

    private String getmonth(int month) {
        if ( month == 1 )  return "JAN" ;       if ( month == 2 )  return "FEB" ;
        if ( month == 3 )  return "MAR" ;       if ( month == 4 )  return "APR" ;
        if ( month == 5 )  return "MAY" ;       if ( month == 6 )  return "JUNE" ;
        if ( month == 7 )  return "JULY" ;      if ( month == 8 )  return "AUG" ;
        if ( month == 9 )  return "SEPT" ;      if ( month == 10 )  return "OCT" ;
        if ( month == 11 )  return "NOV" ;      if ( month == 12 )  return "DEC" ;

        // Default
        return "JAN" ;
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1 ;
                String Date = makeDateString(year,month,dayOfMonth) ;
                date_picker_button.setText(Date);
            }
        } ;

        Calendar calendar = Calendar.getInstance() ;
        int year = calendar.get(Calendar.YEAR) ;
        int month = calendar.get(Calendar.MONTH) ;
        int day = calendar.get(Calendar.DAY_OF_MONTH) ;

        int style = AlertDialog.THEME_HOLO_LIGHT ;

        this.datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);

    }

    public void openDatePicker(View view) {
        this.datePickerDialog.show();
    }

    public void openTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour ;
                minute = selectedMinute ;
                time_picker_button.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute)) ;
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hour,minute,true) ;
        timePickerDialog.setTitle("Select Event Time");
        timePickerDialog.show();
    }
}