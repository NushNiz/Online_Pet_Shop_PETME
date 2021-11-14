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

public class RecycleViewList extends AppCompatActivity {

    RecyclerView recyclerView;
    PostAdapter adapter;
    SearchView searchView;
    Button backHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_list);

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
                        .getReference().child("Pet"),PostModel.class)
                .build();


        adapter=new PostAdapter(options,this);
        recyclerView.setAdapter(adapter);
    }

    private void backtoHome() {
        Intent intent = new Intent(RecycleViewList.this, MainActivity.class);
        startActivity(intent);
    }

    public void addPet(View view){
        Intent intent1 = new Intent(RecycleViewList.this, DonatePet_Activity.class);
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
                        .getReference().child("Pet").orderByChild("Category").startAt(s.toUpperCase()).endAt(s.toLowerCase()+"\ufaff"),PostModel.class)
                .build();


        adapter=new PostAdapter(options,this);
        recyclerView.setAdapter(adapter);

        adapter.startListening();
    }
}