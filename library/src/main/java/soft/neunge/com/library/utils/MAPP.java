package soft.neunge.com.library.utils;

import android.app.Application;

import retrofit2.Retrofit;

/**
 * Created by Charny on 2018-12-04.
 */
public class MAPP extends Application {


    private Retrofit retrofit;
    private static MAPP RAPP;


    public static MAPP getRAPP() {
        return RAPP;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RAPP = this;
    }
}
