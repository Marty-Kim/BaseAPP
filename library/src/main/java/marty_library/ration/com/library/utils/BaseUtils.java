package marty_library.ration.com.library.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Charny on 2018-12-04.
 */
public class BaseUtils {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public static void showSnack(BaseActivity context,String message){
        View rootView = context.findViewById(android.R.id.content);
        Snackbar.make(rootView,message,Snackbar.LENGTH_SHORT).show();
    }
}
