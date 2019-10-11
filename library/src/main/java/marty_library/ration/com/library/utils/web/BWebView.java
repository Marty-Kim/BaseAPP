package marty_library.ration.com.library.utils.web;

import android.content.Context;
import android.webkit.WebView;

public class BWebView extends WebView {



    BWebClient webClient;
    BChromClient chromeClient;
    public BWebView(Context context) {
        super(context);
    }

    public void init(String url){
        initWebView();
        loadUrl(url);
    }




    public void initWebView(){
        webClient = new BWebClient(getContext());
        chromeClient = new BChromClient(getContext());
        setWebChromeClient(chromeClient);
        setWebViewClient(webClient);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setUseWideViewPort(true);
        getSettings().setJavaScriptEnabled(true);

    }



}
