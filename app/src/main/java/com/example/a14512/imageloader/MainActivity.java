package com.example.a14512.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.imagelibrary.ImageLoader;

/**
 * @author 14512
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.img);
        ImageLoader.with(this).load("https://upload-images.jianshu.io/upload_images/" +
                "13133049-dd5bc1b0bf0ee3ae.png?imageMogr2/auto-orient/strip%7CimageView2/" +
                "2/w/597/format/webp").into(imageView);
    }
}
