package marty_library.ration.com.library.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import marty_library.ration.com.library.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    Retrofit retro;
    String base_url;

    public RetrofitClient(String base_url) {
        this.base_url = base_url;
    }

    public OkHttpClient getclient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.connectTimeout(5, TimeUnit.SECONDS); // connect timeout
        builder.addInterceptor(interceptor);
        return builder.build();
    }


    public RetrofitClient() {
        Gson gson = new GsonBuilder().setLenient().create();
        retro = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getclient( ))
                .build();
    }




    public Retrofit getRetro() {
        return retro;
    }
}
