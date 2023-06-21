package com.example.sirajmunir.community;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sirajmunir on 15/11/2018.
 */

public class chatview extends RecyclerView.ViewHolder{



    TextView cv;
    TextView cvv;

    public chatview(@NonNull View itemView) {

        super(itemView);
        cv=itemView.findViewById(R.id.chattext);
        cvv=itemView.findViewById(R.id.nameee);





    }




}
