package com.example.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBClass extends SQLiteOpenHelper {
    private static final String ID_COL = "id";

    private static final String DATE_COL = "date";

    private String TABLE_INFO_NAME = "userInfo";
    private static final String NAME_COL = "name";
    private static final String AGE_COL = "age";
    private static final String GENDER_COL = "gender";
    private static final String USERNAME_COL = "username";
    private static final String PASS_COL = "password";

    private String TABLE_DIARY_NAME = "userDiary";
    private static final String NOTE_COL = "note";
    private static final String ACT_COL = "activities";

    private String TABLE_TRACKER_NAME = "userTracker";
    private static final String MOOD_COL = "mood";
    private static final String ANX_COL = "anxiety";
    private static final String MED_COL = "medicalAdherence";

    public DBClass(@Nullable Context context, @Nullable String name) {
        super(context, name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryReg = "CREATE TABLE " + TABLE_INFO_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_COL + " TEXT,"
                + AGE_COL + " INTEGER," + GENDER_COL + " TEXT," + USERNAME_COL + " TEXT,"
                + PASS_COL + " TEXT)";

        String queryDia = "CREATE TABLE " + TABLE_DIARY_NAME + " ("
                + USERNAME_COL + " TEXT, " + DATE_COL + " TEXT,"
                + NOTE_COL + " TEXT," + ACT_COL + " INTEGER)";

        String queryTra = "CREATE TABLE " + TABLE_TRACKER_NAME + " ("
                + USERNAME_COL + " TEXT, " + DATE_COL + " TEXT," + MOOD_COL + " INTEGER,"
                + ANX_COL + " INTEGER," + MED_COL + " INTEGER)";

        sqLiteDatabase.execSQL(queryReg);
        sqLiteDatabase.execSQL(queryDia);
        sqLiteDatabase.execSQL(queryTra);
    }

    public void addUserInfo(String name, String age, String gender, String username, String pass) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(NAME_COL, name);
        values.put(AGE_COL, Integer.parseInt(age));
        values.put(GENDER_COL, gender);
        values.put(USERNAME_COL, username);
        values.put(PASS_COL, pass);
        db.insert(TABLE_INFO_NAME, null, values);
        db.close();
    }

    public void addDiaryInfo(String username, String date, String notes, int activities) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(USERNAME_COL, username);
        values.put(DATE_COL, date);
        values.put(NOTE_COL, notes);
        values.put(ACT_COL, activities);
        db.insert(TABLE_DIARY_NAME, null, values);
        db.close();
    }

    public void addTrackerInfo(String username, String date, int mood, int anx, int med) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(USERNAME_COL, username);
        values.put(DATE_COL, date);
        values.put(MOOD_COL, mood);
        values.put(ANX_COL, anx);
        values.put(MED_COL, med);
        db.insert(TABLE_TRACKER_NAME, null, values);
        db.close();
    }

    /*public String selectInfoQuery(String fieldname){
        //String fieldvalue;
        String query = "SELECT " + fieldname + " FROM " + TABLE_INFO_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor extract = db.rawQuery(query,null);
        extract.moveToLast();
        return extract.getString(0);
    }*/

    public String selectInfoQuery(String fieldname, String usernameCond){
        String query = "SELECT " + fieldname + " FROM " + TABLE_INFO_NAME + " WHERE " + USERNAME_COL + " = '" + usernameCond + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor extract = db.rawQuery(query,null);
        /*if ((extract != null) && (extract.getCount() > 0)) {
            extract.moveToLast();
            return extract.getString(0);
        } else {
            return "username doesn't exist";
        }*/
        extract.moveToLast();
        return extract.getString(0);
    }

    public String selectDiaQuery(String fieldname, String usernameCond) {
        String query = "SELECT " + fieldname + " FROM " + TABLE_DIARY_NAME + " WHERE " + USERNAME_COL + " = '" + usernameCond + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor extract = db.rawQuery(query,null);
        String out = "";
        if (extract.moveToFirst()) {
            while (extract.isAfterLast() == false) {
                out += extract.getString(0) + "\n";
                extract.moveToNext();
            }
        }
        extract.close();
        return out;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO_NAME);
    }
}
