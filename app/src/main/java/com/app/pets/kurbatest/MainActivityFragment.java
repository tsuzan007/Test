package com.app.pets.kurbatest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by macbookpro on 6/27/17.
 */

public class MainActivityFragment extends Fragment {

    RecyclerView recyclerView;

    private Call<List<Users>> user_list;
    private Call<List<Post>> posts;
    private List<Users> myuser_list=new ArrayList<>();
    private List<Post> myuser_post=new ArrayList<>();
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private UserClient userClient;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activitymain_fragment,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.myrecyclerview);
        myRecyclerViewAdapter=new MyRecyclerViewAdapter();
        layoutManager=new LinearLayoutManager(getActivity());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        userClient=RetrofitClient.getRetrofitClient().create(UserClient.class);
        user_list=userClient.getUsers("users");
        user_list.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                myuser_list=response.body();
                recyclerView.setAdapter(myRecyclerViewAdapter);
                recyclerView.setLayoutManager(layoutManager);
                Log.e(".........","passed");

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.e(".........","failed");

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{
        String addressBean;

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.useritem, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            addressBean=myuser_list.get(position).getAddress().getStreet()+" "+
                    myuser_list.get(position).getAddress().getSuite();

            holder.name.setText(myuser_list.get(position).getName());
            holder.address.setText(addressBean);
            holder.addressCity.setText(myuser_list.get(position).getAddress().getCity());

        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            if(myuser_list==null){
                return 0;
            }
            return myuser_list.size();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
//        @BindView(R.id.user) TextView name;
//        @BindView(R.id.address)  TextView address;
//        @BindView(R.id.addressCity)  TextView addressCity;
        TextView name;
            TextView address;
        TextView addressCity;

        public MyViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   posts= userClient.getPost(getItemViewType());
                    posts.enqueue(new Callback<List<Post>>() {

                        @Override
                        public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                            myuser_post=response.body();
                            Intent intent=new Intent(getActivity(),Main2Activity.class);
                            intent.putParcelableArrayListExtra("post", (ArrayList<? extends Parcelable>) myuser_post);
                            Log.e(".........","passed");
                            startActivity(intent);


                        }

                        @Override
                        public void onFailure(Call<List<Post>> call, Throwable t) {
                            Log.e(".........","failed");

                        }


                    });


                }
            });
            name= (TextView) itemView.findViewById(R.id.user);
            address= (TextView) itemView.findViewById(R.id.address);
            addressCity= (TextView) itemView.findViewById(R.id.addressCity);
//            ButterKnife.bind(this,itemView);
        }
    }
}
