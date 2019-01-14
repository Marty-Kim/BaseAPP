package marty_library.ration.com.library.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Charny on 2019-01-15.
 */
public abstract class BaseSharedPreference {
    Context mCon;
    final String stringdefault = "";
    final int intdefault = -1;
    final float floatdefault = -1.0f;
    final boolean booleandefault = false;

    public BaseSharedPreference(Context context) {
        mCon = context;
        setSharedPreferences(mCon);
    }

    public SharedPreferences sharedPreferences;

    public abstract SharedPreferences setSharedPreferences(Context mCon);

    public String getStringFromShared(String key){
        return sharedPreferences.getString(key,stringdefault);
    }
    public int getIntFromShared(String key) {
        return sharedPreferences.getInt(key, intdefault);
    }
    public float getFloatFromShared(String key) {
        return sharedPreferences.getFloat(key, floatdefault);
    }
    public boolean getBooleanFromShared(String key) {
        return sharedPreferences.getBoolean(key, booleandefault);
    }

    public boolean setStringFromShared(String key,String val){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,val);
        return editor.commit();
    }
    public boolean setIntFromShared(String key,int val) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,val);
        return editor.commit();
    }
    public boolean setFloatFromShared(String key,float val) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key,val);
        return editor.commit();
    }
    public boolean setBooleanFromShared(String key, boolean val) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,val);
        return editor.commit();
    }

}
