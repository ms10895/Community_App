package com.example.sirajmunir.community;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class testing extends AppCompatActivity {

    RecyclerviewA myrc;

    DatabaseReference dr,drr;
    ArrayList<String> data;
    ArrayList<String> userid;
    ArrayList<String> usertasverurl;
    RecyclerView rcc;
    FirebaseAuth authh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
dr= FirebaseDatabase.getInstance().getReference("users");

        data = new ArrayList<>();
        userid=new ArrayList<>();
        usertasverurl=new ArrayList<>();


        authh=FirebaseAuth.getInstance();

        dr.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        for(DataSnapshot ds:dataSnapshot.getChildren()) {

            String key = (String) ds.getKey();


            if (key.equals(authh.getCurrentUser().getUid())) {


                Log.i("test","currenusergaib");

            }
            else
                {


                Log.i("keyhaiye", key);
            drr = FirebaseDatabase.getInstance().getReference("users").child(key);
            drr.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String name =  (String)dataSnapshot.child("name").getValue();
                    String id= (String)dataSnapshot.child("userid").getValue();
                    String url=(String)dataSnapshot.child("photourl").getValue();
                    usertasverurl.add(url);


                    data.add(name);
                    userid.add(id);



                    rcc = findViewById(R.id.recycle);
                    rcc.setLayoutManager(new LinearLayoutManager(testing.this));
                    myrc = new RecyclerviewA(testing.this, data,userid,usertasverurl);
                    rcc.setAdapter(myrc);

                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        }


        Log.i("hanjee",""+data+"");
        //rcc.setAdapter(myrc);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});


      //  data.add("kesa ha");

     //  rcc=findViewById(R.id.recycle);
       // rcc.setLayoutManager(new LinearLayoutManager(testing.this));

       // myrc= new RecyclerviewA(this, data);
       // rcc.setAdapter(myrc);


    }





}
