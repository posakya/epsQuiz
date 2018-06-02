package com.creatu.lokesh.epssathi.db_handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lokesh on 4/8/18.
 */

public class dbHandler extends SQLiteOpenHelper {

    public static final String Db_name = "EpsSathi";
    public static final String table_epsSathi = "epsSathi";
    public static final String id = " _id";
    public static final String title = "eps_title";
    public static final String description = "eps_description";
    public static final String image = "eps_image";
    public static final String flag = "eps_flag";



    public dbHandler(Context context) {
        super(context, Db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + table_epsSathi + "( _id integer primary key autoincrement ,eps_title text,eps_description text ,eps_image text,eps_flag text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("drop table if exists" + table_epsSathi);

    }

    public void insertData(String epsTitle,String epsDesc,String epsImage,String epsFlag){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(title,epsTitle);
        contentValues.put(description,epsDesc);
        contentValues.put(image,epsImage);
        contentValues.put(flag,epsFlag);

        int u = sqLiteDatabase.update(table_epsSathi, contentValues, "eps_title=?", new String[]{epsTitle});
        if (u == 0) {
            sqLiteDatabase.insertWithOnConflict(table_epsSathi, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        }

    }

    public Cursor getData(String flag) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr =  db.rawQuery( "select _id as _id, eps_title, eps_description, eps_image, eps_flag from epsSathi where eps_flag = '"+flag+"'",null);
        if (cr != null) {
            cr.moveToFirst();
        }
        cr.getCount();
        return cr;
    }


}
