package com.example.authenticationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Add_Activity extends AppCompatActivity {

    Button btn_backhome,add_addpets,add_addmedicine,add_addfood,add_addequipemnts,add_addtoys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btn_backhome=findViewById(R.id.btn_backhome);
        add_addpets=findViewById(R.id.add_addpets);
        add_addmedicine=findViewById(R.id.add_addmedicine);
        add_addfood=findViewById(R.id.add_addfood);
        add_addequipemnts=findViewById(R.id.add_addequipemnts);
        add_addtoys=findViewById(R.id.add_addtoys);

        btn_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHome();
            }
        });

        add_addpets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pets();
            }
        });

        add_addmedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicine();
            }
        });

        add_addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food();
            }
        });

        add_addequipemnts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equipments();
            }
        });

        add_addtoys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toys();
            }
        });

    }

    private void pets() {
        Intent intent = new Intent(Add_Activity.this,DonatePet_Activity.class);
        startActivity(intent);
    }

    private void medicine() {
        Intent intent = new Intent(Add_Activity.this,Medicine_Activity.class);
        startActivity(intent);
    }

    private void food() {
        Intent intent = new Intent(Add_Activity.this,Food_Activity.class);
        startActivity(intent);
    }

    private void equipments() {
        Intent intent = new Intent(Add_Activity.this,Equipment_Activity.class);
        startActivity(intent);
    }

    private void toys() {
        Intent intent = new Intent(Add_Activity.this,Toy_Activity.class);
        startActivity(intent);
    }

    private void backHome() {
        Intent intent = new Intent(Add_Activity.this,MainActivity.class);
        startActivity(intent);
    }
}