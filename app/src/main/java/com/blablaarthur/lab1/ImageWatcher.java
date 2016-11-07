package com.blablaarthur.lab1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class ImageWatcher extends AppCompatActivity {

    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_watcher);

        image = (ImageView) findViewById(R.id.imageWatcher);
        Intent currentIntent = getIntent();
        Bitmap bm = BitmapLoader.decodeBitmapFromPath(currentIntent.getStringExtra("Image"),image.getMaxWidth(),image.getMaxHeight());
        image.setImageBitmap(bm);
    }
}
