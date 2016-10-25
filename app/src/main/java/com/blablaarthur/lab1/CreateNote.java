package com.blablaarthur.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class CreateNote extends AppCompatActivity {

    EditText title;
    EditText desc;
    ImageView imp;
    ImageView image;
    TextView time;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        title = (EditText) findViewById(R.id.editTitle);
        desc = (EditText) findViewById(R.id.editDesc);
        imp = (ImageView) findViewById(R.id.imp);
        image = (ImageView) findViewById(R.id.image);
        time = (TextView) findViewById(R.id.textTime);
        date = (TextView) findViewById(R.id.textDate);




        Intent currentIntent = getIntent();
        String action = currentIntent.getAction();
        if(action.equals("android.intent.myaction.CREATE")){
            //title.setText("New note");
            imp.setImageResource(R.drawable.green_dot);
            image.setImageResource(R.drawable.image);
            Calendar now = Calendar.getInstance();
            time.setText(now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE));
            date.setText(now.get(Calendar.DAY_OF_MONTH) + "." + now.get(Calendar.MONTH) + "." + now.get(Calendar.YEAR));
        }
        else{
            title.setText(currentIntent.getStringExtra("Title"));
            int importance = 0;
            switch (currentIntent.getIntExtra("Importance", 0)){
                case 0:
                    importance = R.drawable.green_dot;
                    break;
                case 1:
                    importance = R.drawable.orange_dot;
                    break;
                case 2:
                    importance = R.drawable.red_dot;
                    break;
            }
            imp.setImageResource(importance);
            image.setImageResource(R.drawable.image);
            time.setText(currentIntent.getStringExtra("Time"));
            date.setText(currentIntent.getStringExtra("Date"));
            desc.setText(currentIntent.getStringExtra("Description"));
        }
    }
}
