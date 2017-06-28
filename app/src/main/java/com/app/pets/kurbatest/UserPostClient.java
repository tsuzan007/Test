package com.app.pets.kurbatest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by macbookpro on 6/27/17.
 */

public interface UserPostClient {

    @POST("/posts")
    @FormUrlEncoded
    Call<Post> savePost(@Field("title") String title,
                        @Field("body") String body);

    @DELETE("/posts/{id}")
    Call<ResponseBody> deleteItem(@Path("id") int itemId);

    @PUT("/posts/{postId}")
    @FormUrlEncoded
    Call<Post> update(@Path("postId") int id,@Field("title") String title,
                      @Field("body") String body);
}
