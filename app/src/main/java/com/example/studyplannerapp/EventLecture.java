package com.example.studyplannerapp;

public class EventLecture {
    public EventLecture(int ID, int duration, String subject, String instructor, String date, String time) {
        this.ID = ID;
        this.duration = duration;
        Subject = subject;
        Instructor = instructor;
        this.date = date;
        this.time = time;
    }
    public EventLecture(int duration, String subject, String instructor, String date, String time) {
        this.duration = duration;
        Subject = subject;
        Instructor = instructor;
        this.date = date;
        this.time = time;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getInstructor() {
        return Instructor;
    }

    public void setInstructor(String instructor) {
        Instructor = instructor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private int ID ;
    private int duration ;
    private String Subject ;
    private String Instructor ;
    private String date ;
    private String time ;
}
