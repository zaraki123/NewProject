package com.example.nelson.caliplay;

import android.app.Application;

import com.example.nelson.caliplay.data.DataManager;
import com.example.nelson.caliplay.data.DataManagerImpl;

// to call the DataManager wherever you want without declaring every time it

public class MyApplication extends Application
{

    private DataManager dataManager;
    public DataManager getDataManagerUser() {
        return this.dataManager;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        dataManager = new DataManagerImpl(this);
    }


    @Override
    public void onTerminate() {
        // not guaranteed to be called
        super.onTerminate();
    }
}
