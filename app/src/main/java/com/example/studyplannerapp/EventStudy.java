package com.example.studyplannerapp;

public class EventStudy {
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public EventStudy(int ID, String startDate, String startTime, String title, String description) {
        this.ID = ID;
        this.startDate = startDate;
        this.startTime = startTime;
        this.Title = title;
        this.Description = description;
    }
    public EventStudy(String startDate, String startTime, String title, String description) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.Title = title;
        this.Description = description;
    }

    private int ID ;
    private String startDate ;
    private String startTime ;
    private String Title ;
    private String Description ;

}
