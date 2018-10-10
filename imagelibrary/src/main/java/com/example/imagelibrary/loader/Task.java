package com.example.imagelibrary.loader;

import android.content.Context;

import com.example.imagelibrary.cache.ImageCache;

/**
 * 每一个请求视为一个任务，包装了所有需要的信息
 * @author 14512 on 2018/10/10
 */
public class Task {
    public Context mContext;
    public ImageCache mCache;
    public HttpLoader mHttpLoader;
    public LoaderResult mResult;

    public Task(Context context, ImageCache cache, HttpLoader httpLoader, LoaderResult result) {
        this.mContext = context;
        this.mCache = cache;
        this.mHttpLoader = httpLoader;
        this.mResult = result;
    }
}
