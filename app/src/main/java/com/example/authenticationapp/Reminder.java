package com.example.authenticationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Reminder extends AppCompatActivity implements View.OnClickListener{

    private int notificationid=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        //Set onclick listener
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        EditText editText = findViewById(R.id.editText);
        TimePicker timePicker = findViewById(R.id.timePicker);

        //set notificationId & message
        Intent intent = new Intent(Reminder.this,AlarmReciever.class);
        intent.putExtra("notificationId",notificationid);
        intent.putExtra("message",editText.getText().toString());

        //Pending intent
        PendingIntent alarmIntent = PendingIntent.getBroadcast(
                Reminder.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT
        );

        //Alarm manager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch(view.getId()){

            case R.id.setBtn:
                //set alarm
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                //Create time
                Calendar starttime = Calendar.getInstance();
                starttime.set(Calendar.HOUR_OF_DAY,hour);
                starttime.set(Calendar.MINUTE,minute);
                starttime.set(Calendar.SECOND,0);
                long alarmStartTime = starttime.getTimeInMillis();

                //set Alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);
                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
                //cancel alarm
                alarmManager.cancel(alarmIntent);
                Toast.makeText(this, "Canceled!", Toast.LENGTH_SHORT).show();
                break;
        }

    }


    public void backToToDo(View view){
        Intent intent = new Intent(Reminder.this,ShowActivity.class);
        startActivity(intent);
    }

}