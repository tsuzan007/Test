package com.app.pets.kurbatest;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    RecyclerView mrecyclerview;
    TextView mytitle;
    TextView body;
    List<Post> object;
    private FloatingActionButton floatingActionButton;
    SecondAdapter secondAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mrecyclerview= (RecyclerView) findViewById(R.id.mysecondrecyclerview);
        floatingActionButton=(FloatingActionButton) findViewById(R.id.myfloating);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this, PostActivity.class);
                startActivity(intent);
            }
        });
        secondAdapter=new SecondAdapter();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mrecyclerview.setLayoutManager(layoutManager);
        mrecyclerview.setAdapter(secondAdapter);
        mytitle= (TextView) findViewById(R.id.titlename);
        body= (TextView) findViewById(R.id.mybody);

        Intent intent=getIntent();
        object=intent.getParcelableArrayListExtra("post");
    }

    @Override
    protected void onResume() {
        super.onResume();
        secondAdapter.notifyDataSetChanged();
    }

    public class SecondAdapter extends RecyclerView.Adapter<SecondViewHolder>{

        @Override
        public SecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.userfeeditem, parent, false);
            return new SecondViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SecondViewHolder holder, int position) {
            holder.titleA.setText(object.get(position).getTitle());
            holder.bodyA.setText(object.get(position).getBody());

        }

        @Override
        public int getItemCount() {
            if(object==null){
                return 1;
            }
            if(object.size()==0){
                Toast.makeText(Main2Activity.this,"No feeds from this user",Toast.LENGTH_SHORT).show();
            }
            return object.size();
        }
    }
    public class SecondViewHolder extends RecyclerView.ViewHolder{
        TextView titleA;
        TextView bodyA;

        public SecondViewHolder(View itemView) {
            super(itemView);
            titleA= (TextView) itemView.findViewById(R.id.titlename);
            bodyA= (TextView) itemView.findViewById(R.id.mybody);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.sortingmenu, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//// Handle item selection
//        switch (item.getItemId()) {
//            case R.id.new_game:
//                return true;
//            case R.id.help:
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

}
