package com.example.imagelibrary.cache;

import android.graphics.Bitmap;

/**
 * @author 14512 on 2018/9/22
 */
public class DefaultCache implements ImageCache {
    private MemoryCache mMemoryCache = new MemoryCache();
    private DiskCache mDiskCache = new DiskCache();

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }
}
