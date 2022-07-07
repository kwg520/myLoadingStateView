package wu.com.rxjavalibrary.api;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author:kwg
 * @date:2022/7/6,16:54
 */
public class HttpUtil {

    public static  String BASE_URL = "https://www.wanandroid.com/";

    public static  void setBaseUrl(String baseUrl){
        BASE_URL = baseUrl;
    }

    public static Retrofit getRetrofit(){

        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = okhttpBuilder.addNetworkInterceptor(new StethoInterceptor())
                .readTimeout(10000, TimeUnit.SECONDS)
                .connectTimeout(10000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .build();
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static class HttpLogger implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            Log.d("HttpLogInfo", message);//okHttp的详细日志会打印出来
        }
    }

}
