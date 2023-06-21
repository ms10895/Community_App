package com.example.sirajmunir.community;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by sirajmunir on 15/11/2018.
 */

public class Recyclerviewb extends RecyclerView.Adapter<chatview>{

ArrayList<String> as;
    AppCompatActivity  activity;
    LayoutInflater inflater;
    String msg;
    String caption;



    Recyclerviewb(AppCompatActivity activity, ArrayList<String> as,String name)
    {

        this.as=as;

        this.activity=activity;

        inflater=activity.getLayoutInflater();
        this.caption=name;


    }


    @NonNull
    @Override
    public chatview onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = inflater.inflate(R.layout.chat_item,viewGroup,false);
        return new chatview(v);


    }

    @Override
    public void onBindViewHolder(@NonNull chatview chatview, int i) {

        msg=as.get(i);




        chatview.cv.setText(msg);
        chatview.cvv.setText("TO "+caption+"" );


    }

    @Override
    public int getItemCount() {
       return as.size();
    }
}
