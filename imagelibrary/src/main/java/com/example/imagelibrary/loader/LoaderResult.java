package com.example.imagelibrary.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * @author 14512 on 2018/10/10
 */
public class LoaderResult {
    public static final int RESULT_SUCCESS_FROM_CACHE = 1;
    public static final int RESULT_SUCCESS_FROM_HTTP = 2;

    public ImageView mImageView;
    public String mUrl;
    public Bitmap mBitmap;

    public LoaderResult(ImageView imageView, String url, Bitmap bitmap) {
        this.mImageView = imageView;
        this.mUrl = url;
        this.mBitmap = bitmap;
    }

    public LoaderResult() {

    }
}
