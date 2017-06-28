package com.app.pets.kurbatest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by macbookpro on 6/27/17.
 */

public class RetrofitClient {
    static String base_url="http://jsonplaceholder.typicode.com/";


    public static Retrofit getRetrofitClient(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient= new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();


        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
