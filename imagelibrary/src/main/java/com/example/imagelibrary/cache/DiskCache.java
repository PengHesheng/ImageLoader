package com.example.imagelibrary.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.imagelibrary.utils.CloseUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author 14512 on 2018/9/22
 */
public class DiskCache implements ImageCache {
    static String cacheDir = "sdcard/cache/";

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeQuietly(fileOutputStream);
        }
    }

    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir + url);
    }
}
