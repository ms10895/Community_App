package com.example.sirajmunir.community;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Signup extends AppCompatActivity {





    FirebaseAuth aut;
    EditText fullname;
    EditText age;
    EditText gender;
    EditText email;
    EditText password;
    EditText dob;
    DatabaseReference myref;
    StorageReference fr;
    FirebaseStorage fs;
   String firbaseimgurl;
   Uri tasveer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullname=(EditText)findViewById(R.id.fname);
        age=(EditText)findViewById(R.id.agee);
        gender=(EditText)findViewById(R.id.genderr);
        email=(EditText)findViewById(R.id.emaill);
        password=(EditText)findViewById(R.id.passwordd);
        dob=(EditText)findViewById((R.id.dofb));
        fs=FirebaseStorage.getInstance();
        aut=FirebaseAuth.getInstance();











    }


    public void uploadim(View v)
    {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select picture"),1);






    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK)
        {
            if(requestCode==1)
            {


                Picasso.get().load(data.getData()).noPlaceholder().centerCrop().fit()
                        .into((ImageView) findViewById(R.id.imageView));




               // fr=fs.getReference("images").child(aut.getCurrentUser().getUid());


               // fr.putFile(data.getData());

                tasveer=data.getData();


                Log.i("pic",""+data.getData()+"");
            }


        }

    }

    public void authh(View v)
    {



        //  StorageReference child=fr.child("user1");










        final String ifullname=fullname.getText().toString().trim();
        final String iemail=email.getText().toString().trim();
        final String iage=age.getText().toString();
        final String igender=gender.getText().toString();
       final String ipas=password.getText().toString();
      final   String idob=dob.getText().toString();


        aut.createUserWithEmailAndPassword(iemail,ipas )
                .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(Signup.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Signup.this, "Authentication success." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            String user_id = aut.getCurrentUser().getUid();
                            myref = FirebaseDatabase.getInstance().getReference("users").child(user_id);
                            final DatabaseReference child = myref.child("userid");
                            child.setValue("" + user_id + "");
                            myref.child("name").setValue("" + ifullname + "");
                            myref.child("email").setValue("" + iemail + "");
                            myref.child("gender").setValue("" + igender + "");
                            myref.child("age").setValue("" + iage + "");
                            myref.child("dob").setValue("" + idob + "");

                            fr = fs.getReference("images").child(aut.getCurrentUser().getUid());


                            fr.putFile(tasveer).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {



                            fr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    Log.i("uriii", "" + uri + "");
                                    myref.child("photourl").setValue("" + uri + "");


                                }
                            });
                        }
                        });


                        }


                    }
                });





    }







}
