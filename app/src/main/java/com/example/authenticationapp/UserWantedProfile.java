package com.example.authenticationapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UserWantedProfile extends AppCompatActivity {

    ImageButton imageButton;
    EditText description,category,telephonenumber,price;
    Button btnaddcourse, btnshowcourse,btn_backhome;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    DatabaseReference mRef1;
    StorageReference mStorage;

    private static final int Gallery_code = 1;
    private Uri imageUri=null;
    ProgressDialog mprograss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_pet);

        imageButton =findViewById(R.id.btn_courseImagBtn);
        description=findViewById(R.id.edt_courseId);
        category=findViewById(R.id.edt_courseName);
        telephonenumber=findViewById(R.id.edt_courseFees);
        price=findViewById(R.id.edt_courseDuration);
        btn_backhome=findViewById(R.id.btn_backhome);
        mprograss=new ProgressDialog(this);

        btnaddcourse=findViewById(R.id.btn_addedMedicine);
        btnshowcourse=findViewById(R.id.btn_showMedicine);
        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference().child("Wanted");
        mRef1=mDatabase.getReference().child("User");
        mStorage= FirebaseStorage.getInstance().getReference();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_code);

            }

        });
        btn_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackToHome();
            }
        });
    }

    private void BackToHome() {
        Intent intent = new Intent(UserWantedProfile.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_code && resultCode == RESULT_OK)
        {
            imageUri = data.getData();
            imageButton.setImageURI(imageUri);
        }

        btnaddcourse.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descr=description.getText().toString().trim();
                String cat=category.getText().toString().trim();
                String tel=telephonenumber.getText().toString().trim();
                String pric=price.getText().toString().trim();

                if(!descr.isEmpty() && !cat.isEmpty() && !tel.isEmpty() && !pric.isEmpty() && imageUri!=null)
                {

                    mprograss.setMessage("uploading....!");
                    mprograss.show();

                    StorageReference fillpath = mStorage.child("image_post").child(imageUri.getLastPathSegment());
                    fillpath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    String t = task.getResult().toString();

                                    DatabaseReference newPost=mRef.push();
                                    DatabaseReference newPost1=mRef1.push();

                                    newPost.child("Description").setValue(descr);
                                    newPost.child("Category").setValue(cat);
                                    newPost.child("Telephonenumber").setValue(tel);
                                    newPost.child("Price").setValue(pric);
                                    newPost.child("image").setValue(task.getResult().toString());


                                    newPost1.child("Description").setValue(descr);
                                    newPost1.child("Category").setValue(cat);
                                    newPost1.child("Telephonenumber").setValue(tel);
                                    newPost1.child("Price").setValue(pric);
                                    newPost1.child("image").setValue(task.getResult().toString());


                                    mprograss.dismiss();


                                    Intent intent = new Intent(UserWantedProfile.this,RecyclerViewWanted.class);
                                    startActivity(intent);


                                }
                            });
                        }
                    });
                }
            }
        }));
    }
    public void sendMesssage(View view){
        Intent intent = new Intent(UserWantedProfile.this,RecycleViewList.class);
        startActivity(intent);

    }


}