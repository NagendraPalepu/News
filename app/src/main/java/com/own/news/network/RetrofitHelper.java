package com.own.news.network;

import android.content.Context;

import com.google.gson.Gson;
import com.own.news.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitHelper {

    public static CommonAPI getCommonApi(Context context) {
        return getCommonApi(context, null, null);
    }

    public static CommonAPI getCommonApi(Context context, String baseUrl) {
        return getCommonApi(context, baseUrl, null);
    }

    public static CommonAPI getCommonApi(Context context, Gson gson) {
        return getCommonApi(context, null, gson);
    }

    public static CommonAPI getCommonApi(final Context context, String baseUrl, Gson gson) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(90, TimeUnit.SECONDS);
        builder.connectTimeout(40, TimeUnit.SECONDS);


      if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }



        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl == null ? API.APP_SERVER_URL_REST : baseUrl)
                .client(client)
                .addConverterFactory(gson == null ? GsonConverterFactory.create() : GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        return retrofit.create(CommonAPI.class);
    }



}
