package com.test.before.interview.Component;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashSet;

public class PreferencesManager {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor edit;

    public PreferencesManager(Context context) {
        this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.edit = mSharedPreferences.edit();
    }

    public void setStringPref(String key, String value) {
        edit.putString(key, value);
        edit.apply();
    }

    public void setIntPref(String key, int value) {
        edit.putInt(key, value);
        edit.apply();
    }

    public void setBooleanPref(String key, boolean value) {
        edit.putBoolean(key, value);
        edit.apply();
    }

    public void setMapPref(String key, HashSet value) {
        edit.putStringSet(key, value);
        edit.apply();
    }

    public String getStringPref(String key) {
        return mSharedPreferences.getString(key, null);
    }

    public Boolean getBooleanPref(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public int getIntPref(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public HashSet<String> getMapPref(String key) {
        return (HashSet<String>) mSharedPreferences.getStringSet(key, null);
    }
}
