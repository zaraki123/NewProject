package com.example.nelson.caliplay;

import android.app.Application;

import com.example.nelson.caliplay.data.DataManager;
import com.example.nelson.caliplay.data.DataManagerImpl;

public class MyApplication extends Application
{

    private DataManager dataManager;
    public DataManager getDataManager() {
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
