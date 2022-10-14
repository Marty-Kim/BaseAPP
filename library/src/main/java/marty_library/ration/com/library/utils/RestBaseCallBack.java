package marty_library.ration.com.library.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

import javax.net.ssl.SSLException;

import marty_library.ration.com.library.R;
import retrofit2.Call;
        import retrofit2.Response;


public abstract class RestBaseCallBack<T> extends BaseCallback<T> {
    Activity view;
    Dialog loadingDlg;
    private void showLoading(Activity baseView){
        if (baseView !=null ){
            loadingDlg= new LoadingDlg(baseView);

            loadingDlg.show();
        }
    }

    public RestBaseCallBack(Activity baseView, MartyCall rationCall) {
        super(baseView, rationCall);
        view = baseView;
        showLoading(baseView);

    }
    public RestBaseCallBack(Activity baseView, boolean isLoading, MartyCall rationCall) {
        super(baseView, rationCall);
        if (baseView !=null  && isLoading){
            loadingDlg= new LoadingDlg(baseView);
            loadingDlg.show();
        }
        view = baseView;
        try {
//            dialog.show();
        }catch (Exception e){}

    }
    public RestBaseCallBack(Activity baseView, boolean isLoading, MartyCallWithFailure rationCall) {
        super(baseView, rationCall);
        if (baseView !=null  && isLoading){
            loadingDlg= new LoadingDlg(baseView);
            loadingDlg.show();
        }
        view = baseView;
        try {
//            dialog.show();
        }catch (Exception e){}

    }
    public RestBaseCallBack(Activity baseView, LoadingDlg dlg, MartyCall rationCall) {
        super(baseView, rationCall);
        loadingDlg = dlg;
        if (loadingDlg != null) {
            loadingDlg.show();
        }
        view = baseView;

    }
    public RestBaseCallBack(Activity baseView, LoadingDlg dlg, MartyCallWithFailure rationCall) {
        super(baseView, rationCall);
        loadingDlg = dlg;
        if (loadingDlg != null) {
            loadingDlg.show();
        }
        view = baseView;


    }
    public RestBaseCallBack(Activity baseView, MartyCall rationCall, int failAlertText) {
        super(baseView, rationCall, failAlertText);
        showLoading(baseView);
        view = baseView;
    }

    public RestBaseCallBack(Activity baseView, MartyCallWithFailure rationCall) {
        super(baseView, rationCall);
        showLoading(baseView);
        view = baseView;
    }

    public RestBaseCallBack(Activity baseView, MartyCallWithFailure rationCall, int failAlertText) {
        super(baseView, rationCall, failAlertText);
        showLoading(baseView);
        view = baseView;
    }

    public RestBaseCallBack(Activity baseView, MartyCall rationCall, String failAlertText) {
        super(baseView, rationCall, failAlertText);
        showLoading(baseView);
        view = baseView;
    }

    public RestBaseCallBack(Activity baseView, MartyCallWithFailure rationCall, String failAlertText) {
        super(baseView, rationCall, failAlertText);
        showLoading(baseView);
        view = baseView;
    }
    @Override
    public void onResponse(Call call, Response response) {
        if (loadingDlg != null  && loadingDlg.isShowing()) {
            try{
                loadingDlg.dismiss();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (response.isSuccessful()){
            // Only Success Code
            super.onResponse(call, response);
        }else{
            handleError(call,response.code(),response.message(),response);
        }
    }
    @Override
    public void onFailure(Call call, Throwable t) {
        super.onFailure(call, t);
        if (loadingDlg != null  && loadingDlg.isShowing()) {
            try{
                loadingDlg.dismiss();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        MDEBUG.debug("error ::: " + t.getMessage());
        if(t instanceof NoConnectivityException || !NetworkStatus.getConnectivityStatus()) {
            // 네트워크 연결 이슈
            onNetworkDisConnected(call);
            return;
        }else if(t  instanceof SSLException){
            //txt_over_size_error  파일 사이즈 용량 초과 에러
            onRequestSizeTooLarge(call);
            return;
        }else{
            handleError(call,-1,t.getMessage(),null);
        }

    }
    abstract void handleError(Call call,int httpCode , String httpMsg , Response<T> body);
    abstract void onNetworkDisConnected(Call call);
    abstract void onRequestSizeTooLarge(Call call);
    public void retry(Call<T> call) { call.clone().enqueue(this);}

}
