package solutions.hamza.hotelorders.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import solutions.hamza.hotelorders.ui.LoginActivity;


public class MyApplication extends Application {

    private static MyPreferenceManager pref;

    @Override
    public void onCreate() {
        super.onCreate();

        getPrefManager(getApplicationContext());
    }

    public static MyPreferenceManager getPrefManager(Context context) {
        if (pref == null) {
            pref = new MyPreferenceManager(context);
        }
        return pref;
    }

    public void logout() {
        pref.clear();

    }

}
