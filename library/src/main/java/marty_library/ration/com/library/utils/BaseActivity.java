package marty_library.ration.com.library.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Charny on 2018-12-04.
 */
public class BaseActivity extends AppCompatActivity {
    protected Context mCon;

    public BaseUtils util;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCon = this.getBaseContext();
        util = new BaseUtils(this);
    }
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void showSnack(String message){
        View rootView = findViewById(android.R.id.content);
        Snackbar.make(rootView,message,Snackbar.LENGTH_SHORT).show();
    }

    public boolean getPackageList(String app_packs) {
        boolean isExist = false;

        PackageManager pkgMgr = getPackageManager();
        List<ResolveInfo> mApps;
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = pkgMgr.queryIntentActivities(mainIntent, 0);

        try {
            for (int i = 0; i < mApps.size(); i++) {
                if (mApps.get(i).activityInfo.packageName.startsWith(app_packs)) {
                    isExist = true;
                    break;
                }
            }
        } catch (Exception e) {
            isExist = false;
        }
        return isExist;
    }


    @TargetApi(21)
    public void setStatusBar(int color) throws Exception{
        if (Build.VERSION.SDK_INT < 21)
            throw new Exception("Low Api");
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(color));
    }

    public boolean checkPermissions(String[] permissions){
        BDEBUG.debug("CHECK DEVICE SDK INT ===== " + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            boolean check = true;
            for (int i = 0 ; i < permissions.length ; i++){
                check &= (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this,permissions[i]));
            }
            return check;
        }else{
            return true;
        }
    }
    public boolean checkActivities(Class target , Class[] lists){
        for (int i = 0 ; i < lists.length ; i++){
            if (target == lists[i])
                return true;
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public  boolean mPermissionRequest(Class activity,int REQ,String[] needPemissions){
            if (!checkPermissions(needPemissions)){
                requestPermissions(needPemissions,REQ);
                return false;
            }else
                return true;
    }
    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    public void showKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(view,0);
    }
    public void clearTaskandgo(Class to){
        clearTaskandgo(to,null);
    }
    public void clearTaskandgo(Class to,Bundle extra){

        Intent inte = new Intent(this, to);
        inte.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        inte.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        inte.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (extra != null)
            inte.putExtras(extra);
        startActivity(inte);
        finish();
    }
    public void moveActivity(Class to){
        moveActivity(to,null,false);
    }
    public void moveActivity(Class to,boolean isfinish){
        moveActivity(to,null,isfinish);
    }
    public void moveActivity(Class to,Bundle extra){
        moveActivity(to,extra,false);
    }
    public void moveActivity(Class to,Bundle extra,boolean isfinish){

        Intent inte = new Intent(this, to);
        if (extra != null)
            inte.putExtras(extra);
        startActivity(inte);
        if (isfinish)
            finish();
    }

}
