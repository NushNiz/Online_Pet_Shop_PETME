package com.example.authenticationapp;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class crud {
    ImageButton imageButton;
    EditText description,category,telephonenumber,price;
    Button btnaddcourse, btnshowcourse,btn_backhome;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    DatabaseReference mRef1;
    StorageReference mStorage;
}
