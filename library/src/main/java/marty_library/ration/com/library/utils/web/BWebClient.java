package marty_library.ration.com.library.utils.web;

import android.content.Context;
import android.webkit.WebViewClient;

public class BWebClient extends WebViewClient {
    Context mCon;

    public BWebClient(Context mCon) {
        super();
        this.mCon = mCon;
    }
}
