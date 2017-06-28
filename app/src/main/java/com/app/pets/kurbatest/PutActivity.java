package com.app.pets.kurbatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.app.pets.kurbatest.POJOs.Post;
import com.app.pets.kurbatest.RetrofitInterface.UserPostClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PutActivity extends AppCompatActivity {
    @BindView(R.id.editTitle2)
    EditText title2;
    @BindView(R.id.editBody2)
    EditText body2;
    @BindView(R.id.button_delete)
    Button delete;
    @BindView(R.id.button_update)
    Button update;

     int id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent=getIntent();
        id= intent.getIntExtra("id",0);
        title2.setText(intent.getStringExtra("title"));
        body2.setText(intent.getStringExtra("body"));

    }

    @OnClick(R.id.button_delete)
    public void onDelete_clicked(){
        UserPostClient userPostClient=RetrofitClient.getRetrofitClient().create(UserPostClient.class);
        if(id!=0) {
            Call<ResponseBody> responseBodyCall=userPostClient.deleteItem(id);
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.e("......","success");
                    onBackPressed();
            }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("......","failed");
                }
            });

        }

    }

    @OnClick(R.id.button_update)
    public void onUpate_clicked(){
        UserPostClient userPostClient=RetrofitClient.getRetrofitClient().create(UserPostClient.class);
        Call<Post> updateendpoint=userPostClient.update(id,title2.getText().toString(),body2.getText().toString());
        updateendpoint.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.e("......","success");
                onBackPressed();


            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("......","failed");

            }
        });

    }
}
