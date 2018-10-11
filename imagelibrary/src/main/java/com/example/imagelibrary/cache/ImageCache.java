package com.example.imagelibrary.cache;

import android.graphics.Bitmap;

/**
 * @author 14512 on 2018/9/22
 */
public interface ImageCache {

    /**
     * 存入
     * @param url
     * @param bitmap
     */
    void put(String url, Bitmap bitmap);

    /**
     * 取出
     * @param url
     * @return
     */
    Bitmap get(String url);

    /**
     * 清楚所有的缓存
     * @return
     */
    @Deprecated
    boolean clearAll();

    /**
     * @return 缓存大小
     */
    long cacheSize();
}
