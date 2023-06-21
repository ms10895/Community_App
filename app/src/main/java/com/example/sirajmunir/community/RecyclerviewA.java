package com.example.sirajmunir.community;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.AdapterView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by sirajmunir on 13/11/2018.
 */

public class RecyclerviewA extends RecyclerView.Adapter<itemv>



{
    ArrayList<String> yewaladata;
    AppCompatActivity  activity;
    LayoutInflater layoutwalainflater;
   String namkadata,userkiid,path;
   ArrayList<String> user;
   ArrayList<String> url;



    RecyclerviewA(AppCompatActivity activity,ArrayList<String> yewaladata,ArrayList<String> user,ArrayList<String> url)
    {

        this.yewaladata=yewaladata;
        this.user=user;
        this.activity=activity;
        this.url=url;
        layoutwalainflater=activity.getLayoutInflater();


    }


    @NonNull
    @Override
    public itemv onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View hanjee = layoutwalainflater.inflate(R.layout.view_itemss,viewGroup,false);
        return new itemv(hanjee);
    }

    @Override
    public void onBindViewHolder(@NonNull itemv itemv, int i) {

        namkadata=yewaladata.get(i);
        path=url.get(i);



        itemv.tv.setText(namkadata);
        Picasso.get().load(""+path+"").centerCrop().fit().into(itemv.useriphoto);
        //Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjZNWQem2L2omFO2eAGCCKg_k6IUPTcaR1w7IANHN2Mp40wHPb").into(itemv.useriphoto);

        Log.i("pah",""+path+"");
        userkiid=user.get(i);

        itemv.clik(namkadata,userkiid,path);








    }

    @Override
    public int getItemCount() {

       return yewaladata.size();

    }






}


