package com.example.authenticationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConvertOzToKg extends AppCompatActivity {

    //oz to kg converter
    Button btn_convert;
    Button bmiCal,homeback;
    TextView tv_kg;
    EditText et_oz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_oz_to_kg);
        //oz to kg converter
        tv_kg=findViewById(R.id.tv_kg);
        et_oz=findViewById(R.id.et_oz);
        btn_convert=findViewById(R.id.btn_convert);
        bmiCal=findViewById(R.id.bmiCal);
        homeback=findViewById(R.id.homeback);

        bmiCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbmical();
            }
        });

        homeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeBack();
            }
        });
    }
    private void openbmical() {
        Intent intent = new Intent(ConvertOzToKg.this, BMI_Calculator.class);
        startActivity(intent);
    }

    //oz to kg converter
    @Override
    protected void onResume() {
        super.onResume();
        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAnswer();
            }
        });
    }
    private void calculateAnswer() {

        Converter calculations = new Converter();
        String temp_value = et_oz.getText().toString();

        if(TextUtils.isEmpty(temp_value)){
            Toast.makeText(this, "Please add a value",
                    Toast.LENGTH_LONG).show();
        }
        else{
            float temp = Float.parseFloat(temp_value);

            // convert
            temp = (float) Converter.Convert(temp);

            tv_kg.setText(new Float(temp).toString());
        }
    }

    public void HomeBack(){
        Intent intent = new Intent(ConvertOzToKg.this,MainActivity.class);
        startActivity(intent);
    }
}