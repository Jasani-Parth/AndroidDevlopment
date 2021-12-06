package com.example.studyplannerapp.ui.calender;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.studyplannerapp.DBHandlerAssignment;
import com.example.studyplannerapp.DBHandlerExam;
import com.example.studyplannerapp.DBHandlerLecture;
import com.example.studyplannerapp.DBHandlerStudy;
import com.example.studyplannerapp.EventAssignment;
import com.example.studyplannerapp.EventExam;
import com.example.studyplannerapp.EventLecture;
import com.example.studyplannerapp.EventStudy;
import com.example.studyplannerapp.R;
import com.example.studyplannerapp.databinding.FragmentGalleryBinding;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime ;
import java.util.Set;

public class CalenderFragment extends Fragment {

    private FragmentGalleryBinding binding;

    DBHandlerStudy dbHandlerStudy ;
    DBHandlerAssignment dbHandlerAssignment ;
    DBHandlerExam dbHandlerExam ;
    DBHandlerLecture dbHandlerLecture ;
    String Date ;

    CompactCalendarView calendarView ;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalenderViewModel galleryViewModel = new ViewModelProvider(this).get(CalenderViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        // Getting Context And Initialize Database
        Context context = getActivity();
        dbHandlerStudy = new DBHandlerStudy(context) ;
        dbHandlerAssignment = new DBHandlerAssignment(context) ;
        dbHandlerExam = new DBHandlerExam(context) ;
        dbHandlerLecture = new DBHandlerLecture(context) ;

        TextView titleMonth = (TextView) root.findViewById(R.id.titleMonth) ;

        calendarView = (CompactCalendarView) root.findViewById(R.id.compactcalendar_view) ;
        calendarView.setUseThreeLetterAbbreviation(true);

        // Section For Marking Event
        List<Long> studyEvent = dbHandlerStudy.getAllDates(dbHandlerStudy) ;
        List<Long> assignmentEvent = dbHandlerAssignment.getAllDates(dbHandlerAssignment) ;
        List<Long> examEvent = dbHandlerExam.getAllDates(dbHandlerExam) ;
        List<Long> lectureEvent = dbHandlerLecture.getAllDates(dbHandlerLecture) ;

        Set<Long> TimeStamps = new HashSet<>() ;

        TimeStamps.addAll(studyEvent) ;
        TimeStamps.addAll(assignmentEvent) ;
        TimeStamps.addAll(examEvent) ;
        TimeStamps.addAll(lectureEvent) ;

        for (Long timeStamp : TimeStamps) {
            Event event = new Event(Color.RED, timeStamp);
            calendarView.addEvent(event);
        }

        getDayDetails(getTodayDate(),root);

        String tryDate = String.valueOf(calendarView.getFirstDayOfCurrentMonth()) ;
        String[] tryDate2 = tryDate.split(" ",6) ;
        String Month = tryDate2[1].toUpperCase(Locale.ROOT) + " " + tryDate2[5] ;
        titleMonth.setText(Month);

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(java.util.Date dateClicked) {

                // let's first get Date of clicked position
                String temp = String.valueOf(dateClicked) ;
                String[] dateArr = temp.split(" ",6) ;
                Date = dateArr[1].toUpperCase(Locale.ROOT) + "  " + Integer.parseInt(dateArr[2]) + "  " + Integer.parseInt(dateArr[5]) ;

                getDayDetails(Date,root);
            }

            @Override
            public void onMonthScroll(java.util.Date firstDayOfNewMonth) {
                String tryDate = String.valueOf(calendarView.getFirstDayOfCurrentMonth()) ;
                String[] tryDate2 = tryDate.split(" ",6) ;
                String Month = tryDate2[1].toUpperCase(Locale.ROOT) + " " + tryDate2[5] ;
                titleMonth.setText(Month);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getTodayDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss") ;
        LocalDateTime now = LocalDateTime.now();
        String todayDate = dateTimeFormatter.format(now).split(" ",2)[0] ;
        String[] details = todayDate.split("/",3) ;
        String month = getmonth(Integer.parseInt(details[1])-1) ;
        int day = Integer.parseInt(details[2]) ;
        int year = Integer.parseInt(details[0]) ;

        return month + "  " + day + "  " + year ;
    }

    public String getmonth(int month) {

        if ( month == 0 )  return "JAN" ;       if ( month == 1 )  return "FEB" ;
        if ( month == 2 )  return "MAR" ;       if ( month == 3 )  return "APR" ;
        if ( month == 4 )  return "MAY" ;       if ( month == 5 )  return "JUNE" ;
        if ( month == 6 )  return "JULY" ;      if ( month == 7 )  return "AUG" ;
        if ( month == 8 )  return "SEPT" ;      if ( month == 9 )  return "OCT" ;
        if ( month == 10 )  return "NOV" ;      if ( month == 11 )  return "DEC" ;

        return "JAN" ;
    }

    @SuppressLint("SetTextI18n")
    public void getDayDetails(String Date, View root) {
        int CountStudyEvent = 0 ;
        int CountAssignmentEvent = 0 ;
        int CountExamEvent = 0 ;
        int CountLectureEvent = 0 ;

        CountStudyEvent += dbHandlerStudy.getAllEventFilterByDate(Date) ;
        CountAssignmentEvent += dbHandlerAssignment.getAllEventFilterByDate(Date) ;
        CountExamEvent += dbHandlerExam.getAllEventFilterByDate(Date) ;
        CountLectureEvent += dbHandlerLecture.getAllEventFilterByDate(Date) ;

        TextView heading = (TextView)  root.findViewById(R.id.todayDate) ;
        heading.setText(Date);
        TextView study = (TextView) root.findViewById(R.id.studyCount) ;
        study.setText(Integer.toString(CountStudyEvent));
        TextView assignment = (TextView) root.findViewById(R.id.assignmentCount) ;
        assignment.setText(Integer.toString(CountAssignmentEvent));
        TextView exam = (TextView) root.findViewById(R.id.examCount) ;
        exam.setText(Integer.toString(CountExamEvent));
        TextView lecture = (TextView) root.findViewById(R.id.lectureCount) ;
        lecture.setText(Integer.toString(CountLectureEvent));
    }

    public boolean getTotalEventFilterByDate(String Date) {

        int exam = dbHandlerExam.getAllEventFilterByDate(Date) ;
        int assignment = dbHandlerAssignment.getAllEventFilterByDate(Date) ;
        int study = dbHandlerStudy.getAllEventFilterByDate(Date) ;
        int lecture = dbHandlerLecture.getAllEventFilterByDate(Date) ;

        return (exam + assignment + study + lecture) > 0;
    }
}