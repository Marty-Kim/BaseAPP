package marty_library.ration.com.library.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Charny on 2018-12-04.
 */
public class BaseCallback<T> implements Callback<T> {

    private final String NO_VALUE = "";
    private final String NO_FAIL_TOAST = "NO_FAIL_TOAST";
    public interface MartyCall<T> {
        void onResponse(Call<T> call, Response<T> response);
    }

    public interface MartyCallWithFailure<T> {
        void onResponse(Call<T> call, Response<T> response);
        void onFailure(@NonNull Call<T> call, @NonNull Throwable t);
    }

    private MartyCall<T> martyCall;
    private MartyCallWithFailure<T> martyCallWithFailure;
    protected Activity baseView;
    private String failAlertText;




    public BaseCallback(Activity baseView, MartyCall<T> rationCall) {
        this.martyCall = rationCall;
        this.baseView = baseView;
        this.failAlertText = NO_VALUE;
    }

    public BaseCallback(Activity baseView, MartyCall<T> rationCall, int failAlertText) {
        this.martyCall = rationCall;
        this.baseView = baseView;
        this.failAlertText = baseView.getResources().getString(failAlertText);

    }

    public BaseCallback(Activity baseView, MartyCallWithFailure<T> rationCall) {
        this.martyCallWithFailure = rationCall;
        this.baseView = baseView;
        this.failAlertText = NO_VALUE;
    }

    public BaseCallback(Activity baseView, MartyCallWithFailure<T> rationCall, int failAlertText) {
        this.martyCallWithFailure = rationCall;
        this.baseView = baseView;
        this.failAlertText = baseView.getResources().getString(failAlertText);
    }

    public BaseCallback(Activity baseView, MartyCall<T> rationCall, String failAlertText) {
        this.martyCall = rationCall;
        this.baseView = baseView;
        this.failAlertText = failAlertText;
    }


    public BaseCallback(Activity baseView, MartyCallWithFailure<T> rationCall, String failAlertText) {
        this.martyCallWithFailure = rationCall;
        this.baseView = baseView;
        this.failAlertText = failAlertText;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (martyCall != null)
                martyCall.onResponse(call, response);
            else
                martyCallWithFailure.onResponse(call, response);
        }else{
            onFailure(call,new Throwable());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        BDEBUG.debug(t.toString());
        if (martyCallWithFailure != null) {
            martyCallWithFailure.onFailure(call, t);
            return;
        }
        if (!failAlertText.equalsIgnoreCase(NO_FAIL_TOAST))
            Toast.makeText(baseView, failAlertText.equals( NO_VALUE )  ? "다시 시도해주세요." :failAlertText, Toast.LENGTH_SHORT).show();
    }
}
