package com.example.nelson.caliplay.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.nelson.caliplay.model.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zaraki on 03/12/2015.
 */
public class ExerciseDao implements Dao<Exercise>  {

    private static final String INSERT = "insert into " + ExerciseTable.TABLE_NAME
            + "(" + ExerciseTable.ExerciseColumns.NAME + ", "
            + ExerciseTable.ExerciseColumns.VIDEOID + ", "
            + ExerciseTable.ExerciseColumns.TYPEOFCONTRATION + ", "
            + ExerciseTable.ExerciseColumns.TYPEOFMOVEMENT + ", "
            + ExerciseTable.ExerciseColumns.SECONDS + ", "
            + ExerciseTable.ExerciseColumns.LEVEL + ", "
            + ExerciseTable.ExerciseColumns.COMPLETED + ") values (?, ?, ?, ?, ?, ?, ?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public ExerciseDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(ExerciseDao.INSERT);
    }

    @Override
    public long save(Exercise entity) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, entity.getExerciseName());
        insertStatement.bindString(2, entity.getVideoId());
        insertStatement.bindString(3, entity.getTypeOfContraction());
        insertStatement.bindString(4, entity.getTypeOfMovement());
        insertStatement.bindDouble(5, entity.getSeconds());
        insertStatement.bindDouble(6, entity.getLevel());
        insertStatement.bindDouble(7, entity.getCompleted());
        System.out.println("salvataggio esercizio effettuato");
        Cursor c = db.rawQuery("SELECT * from " + ExerciseTable.TABLE_NAME, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(ExerciseTable.ExerciseColumns.NAME);
            String name = c.getString(colid);
            System.out.println("GOT STUDENT " + name);
        }

        return insertStatement.executeInsert();
    }

    @Override
    public void update(Exercise entity) {
        final ContentValues values = new ContentValues();
        values.put(ExerciseTable.ExerciseColumns.NAME, entity.getExerciseName());
        values.put(ExerciseTable.ExerciseColumns.VIDEOID, entity.getExerciseName());
        values.put(ExerciseTable.ExerciseColumns.TYPEOFCONTRATION, entity.getTypeOfContraction());
        values.put(ExerciseTable.ExerciseColumns.TYPEOFMOVEMENT, entity.getTypeOfMovement());
        values.put(ExerciseTable.ExerciseColumns.SECONDS, entity.getSeconds());
        values.put(ExerciseTable.ExerciseColumns.LEVEL, entity.getLevel());
        values.put(ExerciseTable.ExerciseColumns.COMPLETED, entity.getCompleted());
        db.update(ExerciseTable.TABLE_NAME, values, BaseColumns._ID + " = ?", new String[] {
            String.valueOf(entity.getId())
        });


    }

    @Override
    public void delete(Exercise entity) {
        if (entity.getId() > 0) {
            db.delete(ExerciseTable.TABLE_NAME, BaseColumns._ID + " = ?", new String[]{String.valueOf(entity.getId())});
        }

    }

    @Override
    public Exercise get(long id) {
        Exercise exercise = null;
        Cursor c = db.query(ExerciseTable.TABLE_NAME, new String[]{
                BaseColumns._ID, ExerciseTable.ExerciseColumns.NAME, ExerciseTable.ExerciseColumns.VIDEOID, ExerciseTable.ExerciseColumns.TYPEOFCONTRATION, ExerciseTable.ExerciseColumns.TYPEOFMOVEMENT, ExerciseTable.ExerciseColumns.SECONDS,
                ExerciseTable.ExerciseColumns.LEVEL, ExerciseTable.ExerciseColumns.COMPLETED}, BaseColumns._ID + " = ?", new String[]{String.valueOf(id)}, null, null, null, "1");
        if (c.moveToFirst()) {
            exercise = this.buildExerciseFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }

        return exercise;
    }

    @Override
    public List<Exercise> getAll() {

        List<Exercise> list = new ArrayList<>();
        Cursor c = db.query(ExerciseTable.TABLE_NAME, new String[]{
                BaseColumns._ID, ExerciseTable.ExerciseColumns.NAME,  ExerciseTable.ExerciseColumns.VIDEOID, ExerciseTable.ExerciseColumns.TYPEOFCONTRATION, ExerciseTable.ExerciseColumns.TYPEOFMOVEMENT,
                ExerciseTable.ExerciseColumns.SECONDS, ExerciseTable.ExerciseColumns.LEVEL, ExerciseTable.ExerciseColumns.COMPLETED}, null, null, null, null, ExerciseTable.ExerciseColumns.NAME, null);

        if (c.moveToFirst()) {
            do {
                Exercise exercise = this.buildExerciseFromCursor(c);
                if (exercise != null) {
                    list.add(exercise);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }

        return list;
    }

    private Exercise buildExerciseFromCursor(Cursor c) {
        Exercise exercise = null;
        if (c != null) {
            exercise = new Exercise("", "", "", "", 0, 0, 0);
            exercise.setId(c.getLong(0));
            exercise.setExerciseName(c.getString(1));
            exercise.setTypeOfContraction(c.getString(2));
            exercise.setTypeOfMovement(c.getString(3));
            exercise.setSeconds(c.getInt(4));
            exercise.setLevel(c.getInt(5));;
            exercise.setCompleted(c.getInt(6));
        }
        return exercise;
    }

    public Exercise find (String name) {
        long exerciseId = 0L;
        String sql = "select _id from " + ExerciseTable.TABLE_NAME
                + " where upper(" + ExerciseTable.ExerciseColumns.NAME + ") = ? limit 1";
        Cursor c = db.rawQuery(sql,
                new String[]
                        {
                                name.toUpperCase()});
        if (c.moveToFirst()) {
            exerciseId = c.getLong(0);
        }

        if (!c.isClosed()) {
            c.close();
        }
        return this.get(exerciseId);
    }

}
