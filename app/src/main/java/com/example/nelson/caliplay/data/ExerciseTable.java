package com.example.nelson.caliplay.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.nelson.caliplay.model.Exercise;

/**
 * Created by Zaraki on 03/12/2015.
 */
public class ExerciseTable {

    public static final String TABLE_NAME = "exercise";

    public static class ExerciseColumns implements BaseColumns {
        public static final String NAME = "name";
        public static final String VIDEOID = "videoId";
        public static final String TYPEOFCONTRATION = "typeOfContration";
        public static final String TYPEOFMOVEMENT = "typeOfMovement";
        public static final String SECONDS = "seconds";
        public static final String REPS = "reps";
        public static final String KG = "kg";
        public static final String SETS = "sets";
        public static final String VOLUME = "volume";
        public static final String FREQUENCY = "frequency";
        public static final String TONNAGE = "tonnage";
        public static final String LEVEL = "level";
        public static final String COMPLETED = "completed";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + ExerciseTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(ExerciseColumns.NAME + " TEXT, ");
        sb.append(ExerciseColumns.VIDEOID + " TEXT, ");
        sb.append(ExerciseColumns.TYPEOFCONTRATION + " TEXT, ");
        sb.append(ExerciseColumns.TYPEOFMOVEMENT + " TEXT, ");
        sb.append(ExerciseColumns.SECONDS + " INTEGER, ");
        sb.append(ExerciseColumns.REPS + " INTEGER, ");
        sb.append(ExerciseColumns.KG + " INTEGER, ");
        sb.append(ExerciseColumns.SETS + " INTEGER, ");
        sb.append(ExerciseColumns.VOLUME + " INTEGER, ");
        sb.append(ExerciseColumns.FREQUENCY + " INTEGER, ");
        sb.append(ExerciseColumns.TONNAGE + " INTEGER, ");
        sb.append(ExerciseColumns.LEVEL + " INTEGER, ");
        sb.append(ExerciseColumns.COMPLETED + " INTEGER ");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ExerciseTable.TABLE_NAME);
        ExerciseTable.onCreate(db);
    }

}
