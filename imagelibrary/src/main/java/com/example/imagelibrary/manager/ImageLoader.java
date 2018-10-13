package com.example.imagelibrary.manager;

import android.content.Context;

/**
 * @author 14512 on 2018/9/22
 */
public final class ImageLoader {
    private static final String TAG = "ImageLoader";

    private ImageLoader() {

    }

    public static RequestManager with(Context context) {
        return RequestManager.getInstance().init(context);
    }



}
