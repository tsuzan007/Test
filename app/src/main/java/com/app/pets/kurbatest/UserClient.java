package com.app.pets.kurbatest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by macbookpro on 6/27/17.
 */

public interface UserClient {

    @GET("{users}")
    Call<List<Users>> getUsers(@Path("users") String url);

    @GET("posts/?")
    Call <List<Post>> getPost(@Query("userId") int id);
}
