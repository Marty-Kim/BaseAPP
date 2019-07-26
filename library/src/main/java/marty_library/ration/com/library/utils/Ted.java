package marty_library.ration.com.library.utils;

import android.content.Context;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;


public class Ted {

    public static String REJECTED_MSG = "If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]";
    public static void permissionReq(Context activity, PermissionListener listener, String[] permissions){

        TedPermission.with(activity)
                .setGotoSettingButton(true)
                .setDeniedMessage(REJECTED_MSG)
                .setPermissionListener(listener)
                .setPermissions(permissions)
                .check();
    }

}
