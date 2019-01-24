package marty_library.ration.com.library.utils;

import android.util.Log;

/**
 * Created by Charny on 2018-12-04.
 */
public class MDEBUG {

    public static void debug(String msg){
        Log.d("<Marty>",buildLogMsg(msg));

    }
    public static void debug(double msg){
        Log.d("<Marty>",buildLogMsg(msg + ""));

    }
    public static void debug(int msg){
        Log.d("<Marty>",buildLogMsg(msg + ""));
    }
    public static String buildLogMsg(String message) {

        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName());
        sb.append("]");
        sb.append(message);

        return sb.toString();

    }
}
