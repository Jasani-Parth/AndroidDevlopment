package com.example.studyplannerapp;

public class EventAssignment {
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    private int ID ;
    private String dueDate ;
    private String dueTime ;
    private String Description ;
    private String Subject ;

    public EventAssignment(int ID, String dueDate, String dueTime, String description, String subject) {
        this.ID = ID;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        Description = description;
        Subject = subject;
    }
    public EventAssignment(String dueDate, String dueTime, String description, String subject) {
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        Description = description;
        Subject = subject;
    }
}
