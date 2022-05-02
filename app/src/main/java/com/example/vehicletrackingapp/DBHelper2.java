package com.example.vehicletrackingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper2 extends SQLiteOpenHelper {
    public static final String DBNAME = "Lists.db";
    public DBHelper2(Context context) {
        super(context, "Lists.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase MyDB2) {
        MyDB2.execSQL("create Table Debtors(name TEXT primary key, contact INT, VRN TEXT, litres TEXT, cost INT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB2, int i, int i1) {
        MyDB2.execSQL("drop Table if exists Debtors");
    }

    public Boolean addDebtorToList(String name, String contact, String VRN, String litres, String cost) {
        SQLiteDatabase MyDB2 = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("contact", contact);
        cv.put("VRN", VRN);
        cv.put("litres", litres);
        cv.put("cost", cost);

        long result = MyDB2.insert("Debtors", null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateList(String name, String contact, String VRN, String litres, String cost){
        SQLiteDatabase MyDB2 =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("contact", contact);
        cv.put("VRN", VRN);
        cv.put("litres", litres);
        cv.put("cost", cost);
        Cursor cursor = MyDB2.rawQuery(" select * from Debtors where name =?", new String[]{name});
        if(cursor.getCount()>0){
            long result= MyDB2.update("Debtors",cv, "name=?",new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return false;
        }
    }

    public Boolean deleteFromList(String VRN){
        SQLiteDatabase MyDB2 = this.getWritableDatabase();
        Cursor cursor = MyDB2.rawQuery("select * from Debtors where VRN=?",new String[]{VRN});
        if(cursor.getCount()>0){
            long result = MyDB2.delete("Debtors","VRN=?", new String[]{VRN});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return false;
        }

    }
    public Cursor getdata(){
        SQLiteDatabase MyDB2 =this.getWritableDatabase();
        Cursor cursor = MyDB2.rawQuery("select * from Debtors", null);
        return cursor;
    }
}
