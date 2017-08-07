package com.arichafamily.thequizzardofozw;

import android.app.Application;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;

public class AppManager extends Application {

    FirebaseUser user;
    int x = 10;

    @Override
    public void onCreate() {
        super.onCreate();
        //the on create of the application
        Log.d("Tomer", "OnCreate of the app");
    }
}
