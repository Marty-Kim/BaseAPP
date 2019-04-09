package marty_library.ration.com.library.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Charny on 2018-12-04.
 */
public class BaseActivity extends AppCompatActivity {
    protected Context mCon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCon = this;
    }
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void showSnack(String message){
        View rootView = findViewById(android.R.id.content);
        Snackbar.make(rootView,message,Snackbar.LENGTH_SHORT).show();
    }

}
