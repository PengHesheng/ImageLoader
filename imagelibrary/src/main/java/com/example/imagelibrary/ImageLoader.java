package com.example.imagelibrary;

import android.widget.ImageView;

import com.example.imagelibrary.config.DisplayConfig;
import com.example.imagelibrary.config.ImageLoaderConfig;

/**
 * @author 14512 on 2018/9/22
 */
public final class ImageLoader {

    private static ImageLoader mInstance;
    private volatile ImageLoaderConfig mConfig;
    private RequestQueue mRequestQueue;

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }

    public void init(ImageLoaderConfig config) {
        mConfig = config;
        mRequestQueue = new RequestQueue(mConfig.threadCount);
        mRequestQueue.start();
    }

    private ImageLoader() {
    }

    public void displayImage(String url, ImageView imageView) {
        displayImage(url, imageView, null, null);
    }

    public void displayImage(String url, ImageView imageView, LoadListener listener) {
        displayImage(url, imageView, null, listener);
    }

    public void displayImage(String url, ImageView imageView, DisplayConfig config,
                              LoadListener listener) {
        BitmapRequest request = new BitmapRequest(imageView, url, config, listener);
        request.displayConfig = request.displayConfig != null
                ? request.displayConfig : mConfig.displayConfig;
        mRequestQueue.add(request);
    }


}
