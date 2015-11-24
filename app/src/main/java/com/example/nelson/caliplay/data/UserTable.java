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
        public static final String AGE = "age";
        public static final String HEIGHT = "height";
        public static final String WEIGHT = "weight";
        public static final String LIFESTYLE = "lifestyle";
        public static final String SPORTPRESENT = "sportpresent";
        public static final String SPORTPAST = "sportpast";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + UserTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(UserColumns.NAME + " TEXT, ");
        sb.append(UserColumns.SEX + " TEXT, ");
        sb.append(UserColumns.AGE + " TEXT, ");
        sb.append(UserColumns.HEIGHT + " INTEGER, ");
        sb.append(UserColumns.WEIGHT + " INTEGER, ");
        sb.append(UserColumns.LIFESTYLE + " TEXT, ");
        sb.append(UserColumns.SPORTPRESENT + " TEXT, ");
        sb.append(UserColumns.SPORTPAST + " TEXT ");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_NAME);
        UserTable.onCreate(db);
    }

}
