package com.example.authenticationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMI_Calculator extends AppCompatActivity {


    //BMI
    EditText et_height;
    EditText et_weight;
    TextView bmi;
    Button btn_calculate;
    Button oztokg, backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);


        //BMI
        et_height=findViewById(R.id.et_height);
        et_weight=findViewById(R.id.et_weight);
        btn_calculate=findViewById(R.id.btn_calculate);
        bmi=findViewById(R.id.bmi);
        oztokg=findViewById(R.id.oztokg);
        backHome=findViewById(R.id.backHome);

        oztokg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConverter();
            }
        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHome();
            }
        });

        btn_calculate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                double height = Double.parseDouble(et_height.getText().toString());
                double weight = Double.parseDouble(et_weight.getText().toString());

                double sum = (weight * 703)/(height * height);;

                bmi.setText(Double.toString(sum));
            }
        });


    }

        private void openConverter(){
            Intent intent = new Intent(BMI_Calculator.this, ConvertOzToKg.class);
            startActivity(intent);
        }

    public void backHome(){
        Intent intent = new Intent(BMI_Calculator.this,MainActivity.class);
        startActivity(intent);
    }

}