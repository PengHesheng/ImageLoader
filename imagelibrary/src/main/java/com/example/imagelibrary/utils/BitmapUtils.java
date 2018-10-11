package com.example.imagelibrary.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author 14512 on 2018/10/10
 */
public final class BitmapUtils {

    private BitmapUtils() {

    }

    /**
     * Bitmap to InputStream
     * @param bitmap
     * @param compressFormat
     * @param quality
     * @return
     */
    public static InputStream bitmapToInputStream(Bitmap bitmap,
                                                  Bitmap.CompressFormat compressFormat,
                                                  int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, quality, baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }

    /**
     * default CompressFormat is jpeg
     * @param bitmap
     * @param quality
     * @return
     */
    public static InputStream bitmapToInputStream(Bitmap bitmap, int quality) {
        return bitmapToInputStream(bitmap, Bitmap.CompressFormat.JPEG, quality);
    }

    /**
     * default quality = 100
     * @param bitmap
     * @return
     */
    public static InputStream bitmapToInputStream(Bitmap bitmap) {
        return bitmapToInputStream(bitmap, 100);
    }

    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

}
