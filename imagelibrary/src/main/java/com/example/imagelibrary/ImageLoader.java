package com.example.imagelibrary;

import android.content.Context;

import com.example.imagelibrary.config.LoaderConfig;
import com.example.imagelibrary.loader.LoaderManger;

/**
 * @author 14512 on 2018/9/22
 */
public final class ImageLoader {

    private ImageLoader() {

    }

    public static LoaderManger with(Context context) {
        return LoaderManger.get(new LoaderConfig.Builder(context).build());
    }

    public static LoaderManger with(LoaderConfig config) {
        return LoaderManger.get(config);
    }



}
