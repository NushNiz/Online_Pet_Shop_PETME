package com.example.authenticationapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
//import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class todo_list extends AppCompatActivity {

    //Todolist
    FirebaseFirestore db;
    private EditText mTitle, mDesc;
    private Button mSaveBtn, mShowBtn;
    private String uTitle,uDesc,uId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);


        //Todo_implementation
        mTitle = findViewById(R.id.edit_title);
        mDesc = findViewById(R.id.edit_desc);
        mSaveBtn = findViewById(R.id.save_btn);
        mShowBtn = findViewById(R.id.showall_btn);
        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mSaveBtn.setText("Update");
            uTitle=bundle.getString("uTitle");
            uId=bundle.getString("uId");
            uDesc=bundle.getString("uDesc");
            mTitle.setText(uTitle);
            mDesc.setText(uDesc);

        }else{
            mSaveBtn.setText("Save");
        }

        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(todo_list.this,ShowActivity.class));
            }
        });


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitle.getText().toString();
                String desc = mDesc.getText().toString();

                Bundle bundle1 = getIntent().getExtras();
                if(bundle1 !=null){
                    String id= uId;
                    updateToFireStore(id,title,desc);
                }else{
                    String id = UUID.randomUUID().toString();
                    saveTofireStore(id, title, desc);
                }


            }
        });


    }

    //Todo_implementation method

    public void updateToFireStore(String id, String title, String desc){
        db.collection("todo_list").document(id).update("Title",title,"Description",desc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(todo_list.this, "Data Updated!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(todo_list.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(todo_list.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveTofireStore(String id, String title, String desc) {

        if (!title.isEmpty() && !desc.isEmpty()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("Id", id);
            map.put("Title", title);
            map.put("Description", desc);

            db.collection("todo_list").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(todo_list.this, "Data saved!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(todo_list.this, "Failed!!", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
        }
    }
    //End of todo-implementation

    public void backToHome(View view){
        Intent intent = new Intent(todo_list.this,MainActivity.class);
        startActivity(intent);
    }

    public void toReminder(View view){
        Intent intent = new Intent(todo_list.this,Reminder.class);
        startActivity(intent);
    }



}


