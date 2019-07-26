package marty_library.ration.com.library.utils;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


import marty_library.ration.com.library.BuildConfig;

public class Libraries {
    //permission
    public static void requestPermissionStorage(final Activity context) {
        //lay hinh tu dien thoai
        if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //the second show request permission
            AlertDialog.Builder builderExplain = new AlertDialog.Builder(context);
            builderExplain.setCancelable(false);
            builderExplain.setMessage("파일 저장소 접근 권한이 필요합니다");
            builderExplain.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, ConstantDataManager.PERMISSION_REQUEST_CODE_EXTERNAL_STORAGE);

                }
            });
            builderExplain.show();
        } else {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, ConstantDataManager.PERMISSION_REQUEST_CODE_EXTERNAL_STORAGE);
        }
    }

    public static void requestPermissionStorageDeny(final Activity context) {
        //deny
        if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //press not allow
            Toast.makeText(context, "파일 저장소 접근 권한이 필요합니다", Toast.LENGTH_SHORT).show();
        } else {
            //press never show again

            AlertDialog.Builder builderExplain = new AlertDialog.Builder(context);
            builderExplain.setCancelable(false);
            builderExplain.setMessage("파일 저장소 접근 권한이 필요합니다");
            builderExplain.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    context.startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)));

                }
            });
            builderExplain.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            builderExplain.show();
        }
    }
}
