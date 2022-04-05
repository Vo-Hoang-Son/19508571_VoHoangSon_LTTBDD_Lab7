package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandlerB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "listDC";
    private static final String TABLE_NAMES = "NAMES";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DatabaseHandlerB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAMES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL  , "  +KEY_NAME + " TEXT )";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
// Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMES);

        // Create tables again
        onCreate(sqLiteDatabase);
    }
    public void addPerson(String name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(KEY_ID, person.getId());
        values.put(KEY_NAME, name);

        // Inserting Rowp
      sqLiteDatabase.insert(TABLE_NAMES, null, values);

        sqLiteDatabase.close(); // Closing database connection

    }
    public List<DiaDiem> getPersons(){
        List<DiaDiem> diaDiems = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAMES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                DiaDiem diaDiem = new DiaDiem(cursor.getInt(0),cursor.getString(1));

                diaDiems.add(diaDiem);
            } while (cursor.moveToNext());
        }
        return diaDiems;
    }
    public boolean deletePerson(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DatabaseHandlerB.TABLE_NAMES, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    return true;
    }
    // code to update the single contact
    public int updatePerson(DiaDiem diaDiem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, diaDiem.getName());

        // updating row
        return db.update(TABLE_NAMES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(diaDiem.getId()) });
    }


}
