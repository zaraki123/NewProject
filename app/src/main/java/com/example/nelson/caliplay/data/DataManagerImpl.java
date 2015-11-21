package com.example.nelson.caliplay.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nelson.caliplay.Constants;
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

    public DataManagerImpl(Context context) {

        this.context = context;

        SQLiteOpenHelper openHelper = new SchemaHelper(this.context);
        db = openHelper.getWritableDatabase();
        Log.i(Constants.LOG_TAG, "DataManagerImpl created, db open status: " + db.isOpen());
        userDao = new UserDao(db);

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

}
