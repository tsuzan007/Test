package com.app.pets.kurbatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    RecyclerView mrecyclerview;
    TextView mytitle;
    TextView body;
    List<Post> object;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mrecyclerview= (RecyclerView) findViewById(R.id.mysecondrecyclerview);
        SecondAdapter secondAdapter=new SecondAdapter();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mrecyclerview.setLayoutManager(layoutManager);
        mrecyclerview.setAdapter(secondAdapter);
        mytitle= (TextView) findViewById(R.id.titlename);
        body= (TextView) findViewById(R.id.mybody);

        Intent intent=getIntent();
        object=intent.getParcelableArrayListExtra("post");
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
}
