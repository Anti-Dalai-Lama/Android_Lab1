package com.blablaarthur.lab1;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.app.Dialog;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class CreateNote extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    EditText title;
    EditText desc;
    ImageView imp;
    ImageView image;
    TextView time;
    TextView date;

    private static final int SELECT_PICTURE = 100;
    private static final int REQUEST_PERMISSION = 10;
    String action = "";
    String image_path = "";
    int importance = 0;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        title = (EditText) findViewById(R.id.editTitle);
        desc = (EditText) findViewById(R.id.editDesc);
        imp = (ImageView) findViewById(R.id.imp);
        image = (ImageView) findViewById(R.id.image);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        time = (TextView) findViewById(R.id.textTime);
        date = (TextView) findViewById(R.id.textDate);

        ActionBar bar = getSupportActionBar();
        bar.setHomeButtonEnabled(true);
        bar.setDisplayUseLogoEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);


        Intent currentIntent = getIntent();
        action = currentIntent.getAction();
        if(action.equals("android.intent.myaction.CREATE")){
            bar.setTitle(R.string.create);
            id = currentIntent.getIntExtra("Id", 0);
            //title.setText("New note");
            imp.setImageResource(R.drawable.green_dot);
            image.setImageBitmap(BitmapLoader.decodeBitmapFromResource(getResources(), R.drawable.image, 60,60));
            Calendar now = Calendar.getInstance();

            time.setText(CalendarConverter.getTime(now));
            date.setText(CalendarConverter.getDate(now));
        }
        else{
            bar.setTitle(R.string.edit);
            if(action.equals("android.intent.myaction.WATCH")){
                title.setFocusable(false);
                desc.setFocusable(false);
                bar.setTitle(R.string.note);
            }
            title.setText(currentIntent.getStringExtra("Title"));
            importance = 0;
            int imp_style = 0;
            switch (currentIntent.getIntExtra("Importance", 0)){
                case 0:
                    importance = 0;
                    imp_style = R.drawable.green_dot;
                    break;
                case 1:
                    importance = 1;
                    imp_style = R.drawable.orange_dot;
                    break;
                case 2:
                    importance = 2;
                    imp_style = R.drawable.red_dot;
                    break;
            }
            imp.setImageResource(imp_style);
            image_path = currentIntent.getStringExtra("Image");
            if(image_path.equals("")){
                image.setImageBitmap(BitmapLoader.decodeBitmapFromResource(getResources(), R.drawable.image, 60,60));
            }
            else{
                image.setImageBitmap(BitmapLoader.decodeBitmapFromPath(image_path,60,60));
            }
            time.setText(currentIntent.getStringExtra("Time"));
            date.setText(currentIntent.getStringExtra("Date"));
            desc.setText(currentIntent.getStringExtra("Description"));
            id = currentIntent.getIntExtra("Id", 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!action.equals("android.intent.myaction.WATCH")) {
            MenuInflater menuInflator = getMenuInflater();
            menuInflator.inflate(R.menu.create_note_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                if(title.getText().length() > 0 && desc.getText().length() > 0){
                    Intent intent = new Intent();
                    intent.putExtra("Title", title.getText().toString());
                    intent.putExtra("Description", desc.getText().toString());
                    intent.putExtra("Date", date.getText().toString());
                    intent.putExtra("Time", time.getText().toString());
                    intent.putExtra("Importance", importance);
                    intent.putExtra("Image", image_path);
                    intent.putExtra("Id", id);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else{
                    Toast.makeText(CreateNote.this, R.string.fillemptyfields, Toast.LENGTH_LONG).show();
                }
        }
        return super.onOptionsItemSelected(item);
    }

    public void ChooseImage(View view){
        if(!action.equals("android.intent.myaction.WATCH")) {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, SELECT_PICTURE);
        }
        else{
            if(!image_path.equals("")){
                Intent myIntent = new Intent(CreateNote.this,
                        ImageWatcher.class);
                myIntent.putExtra("Image", image_path);
                startActivity(myIntent);
            }
            else{
                Toast.makeText(this, "There is no picture in this note", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    image_path = getPathFromURI(selectedImageUri);
                    // Set the image in ImageView
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    //image.setImageURI(selectedImageUri);
                    image.setImageBitmap(BitmapLoader.decodeBitmapFromPath(image_path,60,60));
                }
            }
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        cursor.moveToFirst();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        res = cursor.getString(column_index);
        cursor.close();
        return res;
    }

    public void ChangeImp(View view){
        if(!action.equals("android.intent.myaction.WATCH")){
            ImageView current = (ImageView) view;
            switch (importance){
                case 0:
                    importance = 1;
                    current.setImageResource(R.drawable.orange_dot);
                    break;
                case 1:
                    importance = 2;
                    current.setImageResource(R.drawable.red_dot);
                    break;
                case 2:
                    importance = 0;
                    current.setImageResource(R.drawable.green_dot);
                    break;
            }
        }
    }

    public void setDate(View view){
        if(!action.equals("android.intent.myaction.WATCH")) {
            DatePickerFragment fragment = new DatePickerFragment();
            Bundle args = new Bundle();
            args.putString("Date", date.getText().toString());
            fragment.setArguments(args);
            fragment.show(getFragmentManager(), "date");
        }
    }

    public void setTime(View view){
        if(!action.equals("android.intent.myaction.WATCH")) {
            TimePickerFragment fragment = new TimePickerFragment();
            Bundle args = new Bundle();
            args.putString("Time", time.getText().toString());
            fragment.setArguments(args);
            fragment.show(getFragmentManager(), "time");
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d("A_R_T", (dayOfMonth + "." + month + "." + year));
        Calendar c = Calendar.getInstance();
        c.set(year, month, dayOfMonth);
        date.setText(CalendarConverter.getDate(c));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(2000,2,2, hourOfDay,minute);
        time.setText(CalendarConverter.getTime(c));
    }
}
