package com.example.imagelibrary.config;

import android.content.Context;

import com.example.imagelibrary.cache.DefaultCache;
import com.example.imagelibrary.cache.ImageCache;
import com.example.imagelibrary.loader.HttpLoader;
import com.example.imagelibrary.loader.HttpUrlConnectionLoader;

/**
 * @author 14512 on 2018/10/12
 */
public class LoaderConfiguration {
    private Context mContext;
    private ImageCache mImageCache;
    private HttpLoader mHttpLoader;
    private int threadCount;

    public LoaderConfiguration(Context context) {
        mContext = context;
        mImageCache = new DefaultCache(context);
        mHttpLoader = new HttpUrlConnectionLoader();
        threadCount = Runtime.getRuntime().availableProcessors() + 1;
    }

    public Context getContext() {
        return mContext;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public HttpLoader getHttpLoader() {
        return mHttpLoader;
    }

    public ImageCache getImageCache() {
        return mImageCache;
    }

    public void setImageCache(ImageCache imageCache) {
        mImageCache = imageCache;
    }

    public void setHttpLoader(HttpLoader httpLoader) {
        mHttpLoader = httpLoader;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

}
