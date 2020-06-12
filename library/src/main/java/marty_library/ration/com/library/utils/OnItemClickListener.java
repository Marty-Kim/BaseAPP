package marty_library.ration.com.library.utils;

import android.os.SystemClock;
import android.view.View;

/**
 * Project   : BaseAPP
 * Packages  : marty_library.ration.com.library.utils
 * Author    : Marty
 * Date      : 09/01/2019 / 11:50 AM
 * Comment   :
 */
public abstract  class OnItemClickListener<T> {
    private static final long MIN_CLICK_INTERVAL=1000;

    private long mLastClickTime;

    abstract  void onItemClick(View v,T t);
    public final void onClick(View v ,T t) {
        long currentClickTime= SystemClock.uptimeMillis();
        long elapsedTime=currentClickTime-mLastClickTime;
        mLastClickTime=currentClickTime;

        // 중복 클릭인 경우
        if(elapsedTime<=MIN_CLICK_INTERVAL){
            return;
        }

        // 중복 클릭이 아니라면
        onItemClick(v,t);
    }
}
