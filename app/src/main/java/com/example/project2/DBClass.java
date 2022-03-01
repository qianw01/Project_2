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
    private static final String ACT_C0L = "activities";

    private String TABLE_TRACKER_NAME = "userTracker";
    private static final String MOOD_COL = "mood";
    private static final String ANX_C0L = "anxiety";
    private static final String MED_C0L = "medicalAdherence";

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
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_COL + " TEXT,"
                + NOTE_COL + " TEXT," + ACT_C0L + " INTEGER)";

        String queryTra = "CREATE TABLE " + TABLE_TRACKER_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MOOD_COL + " INTEGER,"
                + ANX_C0L + " INTEGER," + MED_C0L + " INTEGER)";

        sqLiteDatabase.execSQL(queryReg);
        sqLiteDatabase.execSQL(queryDia);
        sqLiteDatabase.execSQL(queryTra);
    }

    public void addUserInfo(String name, String age, String gender, String username, String pass) {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(ID_COL, 1);
        values.put(NAME_COL, name);
        values.put(AGE_COL, Integer.parseInt(age));
        values.put(USERNAME_COL, username);
        values.put(PASS_COL, pass);
        db.insert(TABLE_INFO_NAME, null, values);
        db.close();
    }

    public String selectInfoQuery(String fieldname){
        String fieldvalue;
        String query = "SELECT " + fieldname + " FROM " + TABLE_INFO_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor extract = db.rawQuery(query,null);
        extract.moveToLast();
        return extract.getString(0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO_NAME);

    }
}
