package com.example.sirajmunir.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {


   // ImageView im;
    EditText et1;
    EditText et2;


   // DatabaseReference myref;

    FirebaseAuth authh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       // fab.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View view) {
              //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
          //  }
      //  });

       // im=(ImageView)findViewById(R.id.imageView2);

       // Picasso.get().load("https://png.pngtree.com/element_pic/17/07/12/12df250ea040733f736a2641b7ddba74.jpg").into(im);
      //myref=FirebaseDatabase.getInstance().getReference("community-e1667");
    // DatabaseReference child=myref.child("userrr");
     // child.setValue("siraj");

       et1=(EditText)findViewById(R.id.editText);
       et2=(EditText)findViewById(R.id.editText2);

authh=FirebaseAuth.getInstance();


    }

    public void loginn(View v)
    {

        String email=et1.getText().toString().trim();
        String pass=et2.getText().toString().trim();

        authh.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful())
                        {

                            Toast.makeText(MainActivity.this, "invalid details", Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            startActivity(new Intent(MainActivity.this,Welcome.class));



                        }



                    }
                });




    }



    public void sign(View v){


        startActivity(new Intent(MainActivity.this,Signup.class));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
