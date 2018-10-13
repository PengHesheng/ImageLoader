package com.example.imagelibrary.manager;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.imagelibrary.cache.ImageCache;
import com.example.imagelibrary.config.LoaderConfiguration;
import com.example.imagelibrary.data.LoaderResult;
import com.example.imagelibrary.data.RequestMode;
import com.example.imagelibrary.loader.HttpLoader;

/**
 * @author 14512 on 2018/10/13
 */
public final class RequestManager implements IRequestMode<DisplayManager> {
    private static final String TAG = "RequestManager";
    private LoaderConfiguration mLoaderConfiguration;

    private RequestManager() {

    }

    public static RequestManager getInstance() {
        RequestManager instance = (RequestManager) SingletonManager.getService(
                SingletonManager.REQUEST_MANAGER);
        if (instance == null) {
            instance = new RequestManager();
            SingletonManager.registerService(
                    SingletonManager.REQUEST_MANAGER, instance);
        }
        return instance;
    }

    RequestManager init(Context context) {
        mLoaderConfiguration = new LoaderConfiguration(context);
        return this;
    }

    @Override
    public DisplayManager load(String url) {
        Log.d(TAG, "string url");
        RequestMode requestMode = new RequestMode(url.getClass(), url);
        LoaderResult result = new LoaderResult(requestMode);
        mLoaderConfiguration.setResult(result);
        return DisplayManager.get().init(mLoaderConfiguration);
    }

    @Override
    public DisplayManager load(Uri uri) {
        Log.d(TAG, "Uri uri");
        RequestMode requestMode = new RequestMode(uri.getClass(), uri);
        LoaderResult result = new LoaderResult(requestMode);
        mLoaderConfiguration.setResult(result);
        return DisplayManager.get().init(mLoaderConfiguration);
    }

    @Override
    public DisplayManager load(int resId) {
        Log.d(TAG, "int resId");
        RequestMode requestMode = new RequestMode(Integer.valueOf(resId).getClass(), resId);
        LoaderResult result = new LoaderResult(requestMode);
        mLoaderConfiguration.setResult(result);
        return DisplayManager.get().init(mLoaderConfiguration);
    }

    public RequestManager setImageCache(ImageCache imageCache) {
        this.mLoaderConfiguration.setImageCache(imageCache);
        return this;
    }

    public RequestManager setHttpLoader(HttpLoader httpLoader) {
        this.mLoaderConfiguration.setHttpLoader(httpLoader);
        return this;
    }

    public RequestManager setThreadCount(int threadCount) {
        this.mLoaderConfiguration.setThreadCount(threadCount);
        return this;
    }
}
