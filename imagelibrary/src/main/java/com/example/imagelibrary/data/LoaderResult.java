package com.example.imagelibrary.data;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * @author 14512 on 2018/10/10
 */
public class LoaderResult {
    public static final int RESULT_SUCCESS_FROM_CACHE = 1;
    public static final int RESULT_SUCCESS_FROM_HTTP = 2;
    public static final int RESULT_SUCCESS_FROM_RESOURCE = 3;

    public ImageView mImageView;
    public Bitmap mBitmap;
    private Object mUrl;

    public LoaderResult(Object url) {
        this.mUrl = url;
    }

    public Object getUrl() {
        return mUrl;
    }
}
