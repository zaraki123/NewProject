package com.example.nelson.caliplay.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Zaraki on 21/11/2015.
 */
public final class UserTable {

    public static final String TABLE_NAME = "user";

    public static class UserColumns implements BaseColumns {
        public static final String NAME = "name";
        public static final String SEX = "sex";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + UserTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(UserColumns.NAME + " TEXT, ");
        sb.append(UserColumns.SEX + " TEXT ");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_NAME);
        UserTable.onCreate(db);
    }

}
