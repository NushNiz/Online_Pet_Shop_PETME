package com.example.authenticationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //Home side menuBar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton=findViewById(R.id.btn_pet);
        /* Hooks */

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        /* End of hooks */

        /*Tool bar*/
        setSupportActionBar(toolbar);
        /*End Tool bar*/

        /* Navigation drawer menu */
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /* End Navigation drawer menu */

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        return true;

                    case R.id.nav_logout:

                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        finish();
                        return true;

                    case R.id.nav_vac:
                        startActivity(new Intent(MainActivity.this, ViewVaccines.class));
                        //finish();
                        return true;

                    case R.id.nav_todo:
                        startActivity(new Intent(MainActivity.this, ShowActivity.class));
                        //finish();
                        return true;

                    case R.id.nav_bmi:
                        startActivity(new Intent(MainActivity.this, BMI_Calculator.class));
                        //finish();
                        return true;

                    case R.id.nav_converter:
                        startActivity(new Intent(MainActivity.this, ConvertOzToKg.class));
                        //finish();
                        return true;

                    case R.id.nav_add:
                        startActivity(new Intent(MainActivity.this, RecyclerViewWanted.class));
                        //finish();
                        return true;

                    case R.id.nav_addItems:
                        startActivity(new Intent(MainActivity.this, Add_Activity.class));
                        //finish();
                        return true;

                    case R.id.nav_profile:
                        startActivity(new Intent(MainActivity.this, UserProfile.class));
                        //finish();
                        return true;
                }
                return true;
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonatePet();
            }
        });



    }

    private void DonatePet() {
        Intent intent = new Intent(MainActivity.this,RecycleViewList.class);
        startActivity(intent);
    }

    /*public void donatepet(View view){
        Intent intent5 = new Intent(MainActivity.this,RecycleViewList.class);
        startActivity(intent5);
    }*/
    public void addMedi(View view){
        Intent intent6 = new Intent(MainActivity.this,RecycleViewMedicine.class);
        startActivity(intent6);
    }
    public void addFood(View view){
        Intent intent7 = new Intent(MainActivity.this,RecycleViewFood.class);
        startActivity(intent7);
    }
    public void addEquipment(View view){
        Intent intent8 = new Intent(MainActivity.this,RecyclerViewEquipment.class);
        startActivity(intent8);
    }
    public void addToy(View view){
        Intent intent9 = new Intent(MainActivity.this,RecyclerViewYoy.class);
        startActivity(intent9);
    }

    public void addWanted(View view){
        Intent intent22 = new Intent(MainActivity.this,RecyclerViewWanted.class);
        startActivity(intent22);
    }


    public void onBackPressed(){

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}