package com.app.pets.kurbatest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostActivity extends AppCompatActivity {
    EditText edittitle;
    EditText editBody;
    Button post;
    private Call<Post> postend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        edittitle= (EditText) findViewById(R.id.editTitle);
        editBody= (EditText) findViewById(R.id.editBody);
        post= (Button) findViewById(R.id.button_post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPostClient userPostClient=RetrofitClient.getRetrofitClient().create(UserPostClient.class);
                if(!TextUtils.isEmpty(edittitle.getText().toString()) && !TextUtils.isEmpty(editBody.getText().toString())) {
                    postend = userPostClient.savePost(edittitle.getText().toString(), editBody.getText().toString());
                    postend.enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            Log.e("...", "success");
                            onBackPressed();
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {
                            Log.e("...", "failed");

                        }
                    });
                }



            }
        });


    }
}
