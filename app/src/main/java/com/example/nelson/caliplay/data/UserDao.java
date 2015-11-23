package com.example.nelson.caliplay.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.nelson.caliplay.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zaraki on 21/11/2015.
 */
public class UserDao implements Dao<User> {

    private static final String INSERT = "insert into "
            + UserTable.TABLE_NAME
            + "(" + UserTable.UserColumns.NAME + ","
            + UserTable.UserColumns.SEX + ","
            + UserTable.UserColumns.AGE + ","
            + UserTable.UserColumns.HEIGHT + ","
            + UserTable.UserColumns.WEIGHT + ","
            + UserTable.UserColumns.LIFESTYLE + ","
            + UserTable.UserColumns.SPORTPRESENT + ","
            + UserTable.UserColumns.SPORTPAST + ") values (?, ?, ?, ?, ?, ?, ?, ?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public UserDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(UserDao.INSERT);
    }

    @Override
    public long save(User entity) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, entity.getUsername());
        insertStatement.bindString(2, entity.getSex());
        System.out.println("salvataggio effettuato");
        Cursor c = db.rawQuery("SELECT * from " +
                UserTable.TABLE_NAME, null);
        while (c.moveToNext()) {
            int colid = c.getColumnIndex(UserTable.UserColumns.NAME);
            String name = c.getString(colid);
            System.out.println("GOT STUDENT " + name);
        }
        return insertStatement.executeInsert();

    }

    @Override
    public void update(User entity) {

        final ContentValues values = new ContentValues();
        values.put(UserTable.UserColumns.NAME, entity.getUsername());
        values.put(UserTable.UserColumns.SEX, entity.getSex());
        db.update(UserTable.TABLE_NAME, values, BaseColumns._ID + " = ?", new String[]{
                String.valueOf(entity.getId())
        });
    }

    @Override
    public void delete(User entity) {
        if (entity.getId() > 0) {
            db.delete(UserTable.TABLE_NAME, BaseColumns._ID + " = ?", new String[]{String.valueOf(entity.getId())});
        }
    }

    @Override
    public User get(long id) {
        User user = null;
        Cursor c = db.query(UserTable.TABLE_NAME, new String[]{
                        BaseColumns._ID, UserTable.UserColumns.NAME, UserTable.UserColumns.SEX},
                BaseColumns._ID + " = ?", new String[]{String.valueOf(id)}, null, null, null, "1");
        if (c.moveToFirst()) {
            user = this.buildUserFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<User>();
        Cursor c = db.query(UserTable.TABLE_NAME, new String[]{
                BaseColumns._ID, UserTable.UserColumns.NAME, UserTable.UserColumns.SEX}, null, null, null, null, UserTable.UserColumns.NAME, null);
        if (c.moveToFirst()) {
            do {
                User user = this.buildUserFromCursor(c);
                if (user != null) {
                    list.add(user);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }

    private User buildUserFromCursor(Cursor c) {
        User user = null;
        if (c != null) {
            user = new User(null, null);
            user.setId(c.getLong(0));
            user.setUsername(c.getString(1));
            user.setSex(c.getString(2));
        }
        return user;
    }

    public User find(String name) {
        long userId = 0L;
        String sql = "select _id from " + UserTable.TABLE_NAME
                + " where upper(" + UserTable.UserColumns.NAME + ") = ? limit 1";
        Cursor c = db.rawQuery(sql,
                new String[]
                        {
                                name.toUpperCase()});
        if (c.moveToFirst()) {
            userId = c.getLong(0);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return this.get(userId);
    }


}
