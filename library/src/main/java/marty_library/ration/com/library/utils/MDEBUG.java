package marty_library.ration.com.library.utils;

import android.util.Log;

/**
 * Created by Charny on 2018-12-04.
 */
public class MDEBUG {

    private static String TAG = "<MARTY>";
    private static boolean ISDebug = false;

    public static void setISDebug(boolean ISDebug) {
        MDEBUG.ISDebug = ISDebug;
    }
    public static void setTAG(String TAG) {
        MDEBUG.TAG = TAG;
    }

    public static void debug(String msg){
        if (ISDebug)
            Log.d(TAG,buildLogMsg(msg));

    }
    public static void debug(double msg){
        if (ISDebug)
            Log.d(TAG,buildLogMsg(msg + ""));

    }
    public static void debug(int msg){
        if (ISDebug)
            Log.d(TAG,buildLogMsg(msg + ""));
    }
    public static String buildLogMsg(String message) {

        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

        StringBuilder sb = new StringBuilder();

        sb.append("[");
//        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName());
        sb.append("]");
        sb.append(message);

        return sb.toString();

    }
}
