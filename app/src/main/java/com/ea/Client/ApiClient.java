package com.ea.Client;


/**
 * This is Retrofit API Client to setup Retrofit
 */


import android.app.Activity;

import com.ea.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    // RETROFIT OBJECT
    public static ApiClient Instance;
    public static Retrofit retrofit;
    private static OkHttpClient client;


    /**
     * GET API CLIENT
     */
    private ApiClient(Activity activity) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        Gson gson = new GsonBuilder()
                .create();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        client.dispatcher().cancelAll();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


    /**
     * CREATE API INSTANCE
     * will not catch unauthenticated error
     */
    public static synchronized ApiClient getInstance(Activity activity) {
        if (Instance == null) {
            Instance = new ApiClient(activity);
        }
        return Instance;
    }

    public static void cancelAll() {
        if (client != null) {
            client.dispatcher().cancelAll();
        }
    }

    public ApiInterface getApi() {
        return retrofit.create(ApiInterface.class);
    }

}
