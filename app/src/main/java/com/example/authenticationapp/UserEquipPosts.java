package com.example.authenticationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class UserEquipPosts extends AppCompatActivity {


    RecyclerView recyclerView;
    userEquipAdapter adapter;
    SearchView searchView;
    Button backHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_equip_posts);
        recyclerView =findViewById(R.id.recyclerView_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView=findViewById(R.id.app_bar_search);
        backHome = findViewById(R.id.backHome);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backtoHome();
            }
        });


        FirebaseRecyclerOptions<PostModel> options= new FirebaseRecyclerOptions.Builder<PostModel>()
                .setQuery(FirebaseDatabase.getInstance()
                        .getReference().child("User"),PostModel.class)
                .build();

        FirebaseRecyclerOptions<PostModel> options1= new FirebaseRecyclerOptions.Builder<PostModel>()
                .setQuery(FirebaseDatabase.getInstance()
                        .getReference().child("Equipment"),PostModel.class)
                .build();


        adapter=new userEquipAdapter(options,this);
        adapter=new userEquipAdapter(options1,this);
        recyclerView.setAdapter(adapter);
    }

    private void backtoHome() {
        Intent intent = new Intent(UserEquipPosts.this, MainActivity.class);
        startActivity(intent);
    }

    public void adduEquip(View view){
        Intent intent1 = new Intent(UserEquipPosts.this, userEquipProfile.class);
        startActivity(intent1);
    }



    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.searchbar_menu,menu);
        MenuItem menuItem =menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchData(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void searchData(String s){
        FirebaseRecyclerOptions<PostModel> options= new FirebaseRecyclerOptions.Builder<PostModel>()
                .setQuery(FirebaseDatabase.getInstance()
                        .getReference().child("User").orderByChild("Category").startAt(s.toUpperCase()).endAt(s.toLowerCase()+"\ufaff"),PostModel.class)
                .build();


        adapter=new userEquipAdapter(options,this);
        recyclerView.setAdapter(adapter);

        adapter.startListening();
    }
}