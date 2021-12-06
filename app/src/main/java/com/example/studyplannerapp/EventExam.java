package com.example.studyplannerapp;

public class EventExam {

    private int ID ;
    private String date ;
    private String time ;
    private String Subject ;
    private int examDuration ;
    private String Description ;
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public int getExamDuration() {
        return examDuration;
    }

    public void setExamDuration(int examDuration) {
        this.examDuration = examDuration;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public EventExam(int ID, String date, String time, String subject, int examDuration, String description) {
        this.ID = ID;
        this.date = date;
        this.time = time;
        this.Subject = subject;
        this.examDuration = examDuration;
        this.Description = description;
    }
    public EventExam(String date, String time, String subject, int examDuration, String description) {
        this.date = date;
        this.time = time;
        this.Subject = subject;
        this.examDuration = examDuration;
        this.Description = description;
    }


}
