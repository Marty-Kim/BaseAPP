package marty.ration.com.baseapp.utils;

import android.app.Application;


/**
 * Created by Charny on 2018-12-04.
 */
public class MAPP extends Application {
    private static MAPP RAPP;
    public static MAPP getRAPP() {
        return RAPP;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RAPP = this;
    }
}
