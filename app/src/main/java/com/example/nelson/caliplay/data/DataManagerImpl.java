package com.example.nelson.caliplay.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nelson.caliplay.Constants;
import com.example.nelson.caliplay.model.Exercise;
import com.example.nelson.caliplay.model.User;

import java.util.List;

/**
 * Created by Zaraki on 21/11/2015.
 */
public class DataManagerImpl implements DataManager {

    private static final int DATABASE_VERSION = 1;
    private Context context;
    private SQLiteDatabase db;
    private UserDao userDao;
    private ExerciseDao exerciseDao;

    public DataManagerImpl(Context context) {

        this.context = context;

        SQLiteOpenHelper openHelper = new SchemaHelper(this.context);
        db = openHelper.getWritableDatabase();
        Log.i(Constants.LOG_TAG, "DataManagerImpl created, db open status: " + db.isOpen());
        userDao = new UserDao(db);
        exerciseDao = new ExerciseDao(db);

    }

    public User getUser(long userId) {
        User user = userDao.get(userId);
        return user;
    }

    public List<User> getUserHeaders() {
        return userDao.getAll();
    }

    public User findUser(String name) {
        User user = userDao.find(name);
        return user;
    }

    public long saveUser(User user) {
        long userId = 0L;

        try {
            db.beginTransaction();
            userId = userDao.save(user);
            db.setTransactionSuccessful();
        } catch (android.database.SQLException e) {
            Log.e(Constants.LOG_TAG, "Error saving movie (transaction rolled back)", e);
            userId = 0L;
        } finally {
            db.endTransaction();
        }

        return userId;
    }

    public boolean deleteUser(long userId) {
        boolean result = false;
        try {
            db.beginTransaction();
            User user = getUser(userId);
            if (user != null) {
                userDao.delete(user);
            }
            db.setTransactionSuccessful();
            result = true;
        } catch (android.database.SQLException e) {
            Log.e(Constants.LOG_TAG, "Error deleting user (transaction rolled back)", e);
        } finally {
            db.endTransaction();
        }
        return result;

    }

    public Exercise getExercise(long exerciseId) {
        Exercise exercise = exerciseDao.get(exerciseId);
        return exercise;
    }

    public List<Exercise> getExerciseHeaders() {
        return exerciseDao.getAll();
    }

    public Exercise findExercise(String name) {
        Exercise exercise = exerciseDao.find(name);
        return exercise;
    }

    public long saveExercise(Exercise exercise) {
        long exerciseId = 0L;

        try {
            db.beginTransaction();
            exerciseId = exerciseDao.save(exercise);
            db.setTransactionSuccessful();
        } catch (android.database.SQLException e) {
            Log.e(Constants.LOG_TAG, "Error saving movie (transaction rolled back)", e);
            exerciseId = 0L;
        } finally {
            db.endTransaction();
        }

        return exerciseId;
    }

    public boolean deleteExercise(long exerciseId) {
        boolean result = false;
        try {
            db.beginTransaction();
            Exercise exercise = getExercise(exerciseId);
            if (exercise != null) {
                exerciseDao.delete(exercise);
            }
            db.setTransactionSuccessful();
            result = true;
        } catch (android.database.SQLException e) {
            Log.e(Constants.LOG_TAG, "Error deleting user (transaction rolled back)", e);
        } finally {
            db.endTransaction();
        }
        return result;

    }

}
