package com.example.sirajmunir.community;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by sirajmunir on 13/11/2018.
 */

public class itemv extends RecyclerView.ViewHolder {


TextView tv;
    Intent i;
     ImageView useriphoto;






    public itemv(@NonNull final View itemView) {






        super(itemView);
        tv=itemView.findViewById(R.id.items);
        useriphoto=itemView.findViewById(R.id.imageView2);




    }

    public void clik(final String dataa, final String userid,final String photourl)
    {
       i=new Intent(itemView.getContext(),chat.class);
        i.putExtra("forname",dataa);
         i.putExtra("userid",userid);
         i.putExtra("url",photourl);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           itemView.getContext().startActivity(i);


                Log.i("clickhogea",""+dataa+" ");
                Log.i("clickhogea",""+userid+" "+photourl+"");

            }
        });


    }

}
