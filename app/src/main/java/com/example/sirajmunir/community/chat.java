package com.example.sirajmunir.community;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chat extends AppCompatActivity {


    TextView t1,t2;
    EditText et;
    DatabaseReference msgref,newref;

    String sangikiid;
    FirebaseAuth authen;
    Query q;
     String id;
    ArrayList<String> pegaam;
String nam;
RecyclerView rv;

Recyclerviewb  rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

       // t1=(TextView) findViewById(R.id.textView2);
        t2=(TextView) findViewById(R.id.textView3);
        et=(EditText)  findViewById(R.id.editText3);
        pegaam=new ArrayList<>();

        Log.i("testingalot",getIntent().getStringExtra("forname"));
        Log.i("testingalot",getIntent().getStringExtra("userid"));
        Log.i("testingalot",getIntent().getStringExtra("url"));
        sangikiid=getIntent().getStringExtra("userid");
        nam=getIntent().getStringExtra("forname");

        authen=FirebaseAuth.getInstance();












    }


    public void sendd(View v){

        String msg=et.getText().toString();
        id=""+authen.getCurrentUser().getUid()+""+sangikiid+"";


        msgref= FirebaseDatabase.getInstance().getReference("messages").push();



        msgref.child("senderid").setValue(authen.getCurrentUser().getUid());
        msgref.child("message").setValue(msg);
        msgref.child("recieverid").setValue(sangikiid);

        newref= FirebaseDatabase.getInstance().getReference("messages");
        newref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()) {

                    String chabi = (String) ds.getKey();
                    Log.i("orjee",""+chabi+"");

                   newref= FirebaseDatabase.getInstance().getReference("messages").child(chabi);
                   newref.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                           String msg =  (String)dataSnapshot.child("message").getValue();

                           Log.i("orjee",""+msg+"");
                           pegaam.add(msg);


                           rv = findViewById(R.id.bat);
                           rv.setLayoutManager(new LinearLayoutManager(chat.this));
                           rb = new Recyclerviewb(chat.this, pegaam,nam);
                           rv.setAdapter(rb);

                           Log.i("b",""+pegaam+"");




                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });











                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

















        Log.i("ey",""+msgref.getKey()+"");












    }


}
