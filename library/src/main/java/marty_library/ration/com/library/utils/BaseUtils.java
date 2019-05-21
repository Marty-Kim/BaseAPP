package marty_library.ration.com.library.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static marty_library.ration.com.library.utils.PROPERTY.VALIDATE_EMAIL;
import static marty_library.ration.com.library.utils.PROPERTY.VALIDATE_EMPTY;
import static marty_library.ration.com.library.utils.PROPERTY.VALIDATE_EQUALS1;
import static marty_library.ration.com.library.utils.PROPERTY.VALIDATE_EQUALS2;
import static marty_library.ration.com.library.utils.PROPERTY.VALIDATE_PW;

/**
 * Created by Charny on 2018-12-04.
 */
public class BaseUtils {

    public String pw_regex = "^[A-Za-z0-9]{6,16}\\$";
    private Context mCon;

    public BaseUtils(Context mCon) {
        this.mCon = mCon;
    }

    public void getAppKeyHash(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }
    // TextView underline
    public  void setUnderline(TextView tv, String msg){
        SpannableString content = new SpannableString(msg);
        content.setSpan(new UnderlineSpan(), 0, msg.length(), 0);
        tv.setText(content);
    }
    // email , password regex validation
    public boolean isEmail(String msg){
        return Patterns.EMAIL_ADDRESS.matcher(msg).matches();
    }

    public boolean isPw(String msg){
        return Pattern.matches(pw_regex,msg);
    }
    public boolean isPw(String regex , String msg){
        return Pattern.matches(regex,msg);
    }
    public boolean isValidateChecks(CheckBox[] checkBoxes){
        for (CheckBox box : checkBoxes){
            if (!box.isChecked())
                return false;
        }
        return true;
    }

    public void postRunnable(Runnable r , long millis){
        new Handler().postDelayed(r,millis);
    }


    // TextView[] Validation from type Map
    public boolean isValidationForms(Map<String , TextView> params){

        boolean isvalidate = true;
        for(Map.Entry<String, TextView> elem : params.entrySet() ){
            String key = elem.getKey();
            TextView tv = elem.getValue();
            if (tv == null || key == null || key.isEmpty())
                continue;

            String equalsmsg1 , equalsmsg2;
            equalsmsg1 = equalsmsg2 = null;
            switch (key){
                case VALIDATE_EMPTY:
                    isvalidate &= !tv.getText().toString().isEmpty();
                    break;
                case VALIDATE_EQUALS1:
                    equalsmsg1 = tv.getText().toString();
                    break;
                case VALIDATE_EQUALS2:
                    equalsmsg2 = tv.getText().toString();
                    break;
                case VALIDATE_EMAIL:
                    isvalidate &= isEmail(tv.getText().toString());
                    break;
                case VALIDATE_PW:
                    isvalidate &= isPw(tv.getText().toString());
                    break;
            }
            if (equalsmsg1 != null && equalsmsg2 != null)
                isvalidate &= equalsmsg1.equals(equalsmsg2);

            return isvalidate;

        }


        return isvalidate;
    }

    // Format ###,###
    public String MoneyFomatter(String msg){
        Format format = new DecimalFormat("###,###");
        return format.format(msg);
    }
    public String MoneyFomatter(int msg){
        return String.format("%,d",msg);
    }

    public int moneyToInt(String money_str){

        try {
            return Integer.parseInt(money_str.replaceAll(",", ""));
        }catch (Exception e){
            return 0;
        }
    }
    public Spanned fromHtml(String html) {

        Spanned result;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);

        } else {

            result = Html.fromHtml(html);
        }

        return result;
    }

    public Drawable getDrawable(Context context, int resourceId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(resourceId);
        }

        return context.getResources().getDrawable(resourceId);

    }
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getColor(Context context, int resourceId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(resourceId);
        }

        return context.getResources().getColor(resourceId);
    }

    public int convertDipToPixels(Context mCon ,float dp)
    {
        return (int) (dp * mCon.getResources().getDisplayMetrics().density + 0.5f);
    }


}
