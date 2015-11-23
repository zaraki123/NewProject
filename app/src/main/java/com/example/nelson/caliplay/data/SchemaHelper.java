package com.example.nelson.caliplay.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nelson.caliplay.Constants;
import com.example.nelson.caliplay.data.UserTable;

/**
 * Created by Zaraki on 21/11/2015.
 */
public class SchemaHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "caliplay.db";
    private static final int DATABASE_VERSION = 1;

    SchemaHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    @Override
    public void onCreate(final SQLiteDatabase db) {

        UserTable.onCreate(db);

    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        Log.i(Constants.LOG_TAG, "SQLiteOpenHelper onUpgrade - oldVersion:" + oldVersion + " newVersion:" + newVersion);
    }
}
