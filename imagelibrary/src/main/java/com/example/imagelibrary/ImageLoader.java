package com.example.imagelibrary;

import android.content.Context;

import com.example.imagelibrary.loader.LoaderManger;

/**
 * @author 14512 on 2018/9/22
 */
public final class ImageLoader {

    private ImageLoader() {

    }

    public static LoaderManger with(Context context) {
        return LoaderManger.get(context);
    }



}
