package com.example.authenticationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RecyclerViewEquipment extends AppCompatActivity {

    RecyclerView recyclerView;
    PostEquipmentAdapter adapter;
    SearchView searchView;
    Button backHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_equipment);

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
                        .getReference().child("Equipment"),PostModel.class)
                .build();


        adapter=new PostEquipmentAdapter(options,this);
        recyclerView.setAdapter(adapter);
    }

    private void backtoHome() {
        Intent intent = new Intent(RecyclerViewEquipment.this, MainActivity.class);
        startActivity(intent);
    }

    public void AddEquipment(View view){
        Intent intent = new Intent(RecyclerViewEquipment.this, Equipment_Activity.class);
        startActivity(intent);
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
                        .getReference().child("Equipment").orderByChild("Category").startAt(s.toUpperCase()).endAt(s.toLowerCase()+"\ufaff"),PostModel.class)
                .build();


        adapter=new PostEquipmentAdapter(options,this);
        recyclerView.setAdapter(adapter);

        adapter.startListening();
    }
}