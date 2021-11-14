package com.example.authenticationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class ViewVaccines extends AppCompatActivity {

    private RecyclerView vaccine_rv;
    FirebaseFirestore db;
    private VaccineAdapter vaccineAdapter;
    private List<VaccineModel> list;

    Button btn_addvaccines,backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vaccines);

        btn_addvaccines = findViewById(R.id.btn_addvaccines);
        backHome = findViewById(R.id.backHome);


        btn_addvaccines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVaccines();
            }
        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backtoHome();
            }
        });

        vaccine_rv = findViewById(R.id.vaccine_rv);
        vaccine_rv.setHasFixedSize(true);
        vaccine_rv.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        vaccineAdapter = new VaccineAdapter(this,list);
        vaccine_rv.setAdapter(vaccineAdapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new VaccineTouchHelper(vaccineAdapter));
        touchHelper.attachToRecyclerView(vaccine_rv);

        showData();
    }

    private void addVaccines() {
        Intent intent = new Intent(ViewVaccines.this, Vaccination.class);
        startActivity(intent);
    }

    private void backtoHome() {
        Intent intent = new Intent(ViewVaccines.this, MainActivity.class);
        startActivity(intent);
    }



    public void showData() {

        db.collection("Documents").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()) {
                    VaccineModel model = new VaccineModel(snapshot.getString("id"), snapshot.getString("name"), snapshot.getString("got"), snapshot.getString("description"), snapshot.getString("date"));
                    list.add(model);
                }
                vaccineAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ViewVaccines.this,"Oops...Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}