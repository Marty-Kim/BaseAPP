package marty_library.ration.com.library.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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

}
