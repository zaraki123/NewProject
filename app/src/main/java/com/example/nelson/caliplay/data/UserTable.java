package com.example.nelson.caliplay.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Zaraki on 21/11/2015.
 */
public final class UserTable {

    public static final String TABLE_NAME = "user";

    public static class UserColumns implements BaseColumns {
        public static final String NAME = "";
        public static final String SEX = "";
        public static final String AGE = "";
        public static final int HEIGHT = 0;
        public static final int WEIGHT = 0;
        public static final String LIFESTYLE = "";
        public static final String SPORTPRESENT = "";
        public static final String SPORTPAST = "";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + UserTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(UserColumns.NAME + " TEXT, ");
        sb.append(UserColumns.SEX + " TEXT, ");
        sb.append(UserColumns.AGE + " TEXT, ");
        sb.append(UserColumns.HEIGHT + " INT, ");
        sb.append(UserColumns.WEIGHT + " INT, ");
        sb.append(UserColumns.LIFESTYLE + " TEXT, ");
        sb.append(UserColumns.SPORTPRESENT + " TEXT, ");
        sb.append(UserColumns.SPORTPAST + " TEXT");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_NAME);
        UserTable.onCreate(db);
    }

}
