package org.maktab36.dictionaryapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.maktab36.dictionaryapp.database.DictionaryDBSchema.*;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context) {
        super(context, DictionaryDBSchema.NAME, null, DictionaryDBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ WordTable.NAME + "("+
                WordTable.COLS.ID+" integer primary key autoincrement,"+
                WordTable.COLS.UUID+" text,"+
                WordTable.COLS.ENGLISH+" text,"+
                WordTable.COLS.PERSIAN+" text,"+
                WordTable.COLS.ARABIC+" text,"+
                WordTable.COLS.FRENCH+" text"+
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
