package com.example.studyplannerapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DBHandlerAssignment extends SQLiteOpenHelper {

    public DBHandlerAssignment(Context context) {
        super(context,"DataBaseAssignmentEvent",null,1);
    }

    public DBHandlerAssignment(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE myassignmentevent (ID INTEGER PRIMARY KEY, Subject TEXT, Description TEXT, dueDate TEXT, dueTime TEXT)";
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop = String.valueOf("DROP TABLE IF EXISTS");
        sqLiteDatabase.execSQL(drop, new String[]{"myassignmentevent"});
        onCreate(sqLiteDatabase);
    }

    public void addAssignmentEvent(EventAssignment eventAssignment) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Subject", eventAssignment.getSubject());
        values.put("Description", eventAssignment.getDescription());
        values.put("dueDate", eventAssignment.getDueDate());
        values.put("dueTime", eventAssignment.getDueTime());

        Long k = sqLiteDatabase.insert("myassignmentevent", null, values);
        Log.d("mytag", Long.toString(k));
        sqLiteDatabase.close();
    }

    public void getAssignmentEvent(int ID) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("myassignmentevent", new String[]{"ID", "Subject", "Description", "dueDate", "dueTime"},
                "ID=?", new String[]{String.valueOf(ID)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Log.d("mytag", cursor.getString(1));
            Log.d("mytag", cursor.getString(2));
            Log.d("mytag", cursor.getString(3));
            Log.d("mytag", cursor.getString(4));
        } else {
            Log.d("mytag", "Error Raised!!");
        }
    }

    public List<EventAssignment> getAllEvents(){
        List<EventAssignment> assignmentList = new ArrayList<>() ;
        SQLiteDatabase db = this.getReadableDatabase() ;

        //Generate the query to read from the database
        String select = "SELECT * FROM " + "myassignmentevent";
        Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToFirst()){
            do {
                EventAssignment obj = new EventAssignment(Integer.parseInt(cursor.getString(0)),cursor.getString(3),cursor.getString(4),cursor.getString(2),cursor.getString(1)) ;
                assignmentList.add(obj) ;
            } while (cursor.moveToNext()) ;
        }
        return assignmentList ;
    }

    public int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorEvents = db.rawQuery("SELECT * FROM " + "myassignmentevent", null);
        return cursorEvents.getCount();
    }

    public List<Long> getAllDates(DBHandlerAssignment dbHandlerAssignment) {
        List<EventAssignment> studyList = dbHandlerAssignment.getAllEvents() ;
        List<Long> dates = new ArrayList<>() ;

        for ( EventAssignment obj : studyList ) {
            String[] D = obj.getDueDate().split("  ",3) ;
            int day  = Integer.parseInt(D[1]) ;
            int year = Integer.parseInt(D[2]) ;
            int month = getmonthIndex(D[0]) ;

            dates.add(getEpochNumber(year,month,day)) ;
        }
        return dates ;
    }

    public Long getEpochNumber(int year , int month , int day ) {
        long num = 0 ;

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateString = Integer.toString(day)+"-"+Integer.toString(month+1)+"-"+Integer.toString(year)+" 11:18:32" ;

        try{
            Date date = sdf.parse(dateString) ;
            Calendar calendar = Calendar.getInstance() ;
            assert date != null;
            calendar.setTime(date);
            num = calendar.getTimeInMillis() ;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return num;
    }

    public int getmonthIndex(String month) {

        if (month.equals("JAN")) return 0;       if (month.equals("FEB")) return 1;
        if (month.equals("MAR")) return 2;       if (month.equals("APR")) return 3;
        if (month.equals("MAY")) return 4;       if (month.equals("JUNE")) return 5 ;
        if (month.equals("JULY")) return  6;      if (month.equals("AUG")) return 7;
        if (month.equals("SEPT")) return  8;      if (month.equals("OCT")) return 9;
        if (month.equals("NOV")) return 10;      if (month.equals("DEC")) return 11;

        return 0 ;
    }

    public int getAllEventFilterByDate(String Date) {
        int Count = 0 ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorEvents = db.rawQuery("SELECT * FROM " + "myassignmentevent", null);

        if (cursorEvents.moveToFirst()) {
            do {
                if (cursorEvents.getString(3).equals(Date)) {
                    Count++ ;
                }
            } while (cursorEvents.moveToNext()) ;
        }
        return Count ;
    }
    public void deleteEvent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("myassignmentevent","ID=?",new String[]{String.valueOf(id)});
        db.close();
    }
}