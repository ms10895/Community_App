package com.example.sirajmunir.community;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Welcome extends AppCompatActivity {



    TextView name,email,age,dob,gender;
    ImageView iv;

DatabaseReference myref;
FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
name=(TextView)findViewById(R.id.textView5);
        email=(TextView)findViewById(R.id.textView6);
        age=(TextView)findViewById(R.id.textView7);
        dob=(TextView)findViewById(R.id.textView8);
        gender=(TextView)findViewById(R.id.textView9);
        iv=(ImageView)findViewById(R.id.imageView3);


auth=FirebaseAuth.getInstance();


myref=FirebaseDatabase.getInstance().getReference("users").child(auth.getCurrentUser().getUid());

myref.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String namee=dataSnapshot.child("name").getValue().toString();
        String emai=dataSnapshot.child("email").getValue().toString();
        String ageee=dataSnapshot.child("age").getValue().toString();
        String dateofbirth=dataSnapshot.child("dob").getValue().toString();
        String genderrr=dataSnapshot.child("gender").getValue().toString();
        String photo=dataSnapshot.child("photourl").getValue().toString();


        name.setText(namee);
        email.setText(emai);
       age.setText(ageee);
       dob.setText(dateofbirth);
       gender.setText(genderrr);

        Picasso.get().load(""+photo+"").centerCrop().fit().into(iv);







    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});






    }




    public void dost(View v)
    {

        startActivity(new Intent(Welcome.this,testing.class));


    }



}
