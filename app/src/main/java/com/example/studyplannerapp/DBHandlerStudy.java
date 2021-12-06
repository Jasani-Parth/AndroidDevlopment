package com.example.studyplannerapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DBHandlerStudy extends SQLiteOpenHelper {

    public DBHandlerStudy(Context context) {
        super(context, "DataBaseStudyEvent", null, 1);
    }

    public DBHandlerStudy(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE mystudyevent (ID INTEGER PRIMARY KEY, Title TEXT, Description TEXT , startDate TEXT, startTime TEXT)";
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop = String.valueOf("DROP TABLE IF EXISTS");

        sqLiteDatabase.execSQL(drop, new String[]{"mystudyevent"});
        onCreate(sqLiteDatabase);
    }

    public void addStudyEvent(@NonNull EventStudy eventStudy) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Title", eventStudy.getTitle());
        values.put("Description", eventStudy.getDescription());
        values.put("startDate", eventStudy.getStartDate());
        values.put("startTime", eventStudy.getStartTime());

        long k = sqLiteDatabase.insert("mystudyevent", null, values);

        Log.d("mytag", Long.toString(k));
        sqLiteDatabase.close();
    }

    public void getStudyEvent(int ID) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("mystudyevent"
                , new String[]{"ID", "Title", "Description", "startDate", "startTime"}
                , "ID=?"
                , new String[]{String.valueOf(ID)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Log.d("mytag", cursor.getString(1));
            Log.d("mytag", cursor.getString(2));
            Log.d("mytag", cursor.getString(3));
            Log.d("mytag", cursor.getString(4));
        } else {
            Log.d("mytag", "some error!!");
        }
    }

    public List<EventStudy> getAllEvents() {
        List<EventStudy> studyList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //Generate the query to read from the database
        String select = "SELECT * FROM " + "mystudyevent";
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if (cursor.moveToFirst()) {
            do {
                EventStudy obj = new EventStudy(Integer.parseInt(cursor.getString(0)), cursor.getString(3), cursor.getString(4), cursor.getString(1), cursor.getString(2));
                studyList.add(obj);
            } while (cursor.moveToNext());
        }
        return studyList;
    }

    public int getCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorEvents = db.rawQuery("SELECT * FROM " + "mystudyevent", null);
        return cursorEvents.getCount();
    }

    public int getAllEventFilterByDate(String Date) {
        int Count = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorEvents = db.rawQuery("SELECT * FROM " + "mystudyevent", null);

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
        db.delete("mystudyevent","ID=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Long> getAllDates(DBHandlerStudy dbHandlerStudy) {
        List<EventStudy> studyList = dbHandlerStudy.getAllEvents() ;
        List<Long> dates = new ArrayList<>() ;

        for ( EventStudy obj : studyList ) {
            String[] D = obj.getStartDate().split("  ",3) ;
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

}