package com.example.imagelibrary.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.example.imagelibrary.utils.HashKeyUtils;

/**
 * 内存缓存
 * @author 14512 on 2018/9/22
 */
class MemoryCache implements ImageCache {
    /**
     * 图片缓存
     */
    private LruCache<String, Bitmap> mImageCache;

    MemoryCache() {
        initImageCache();
    }

    private void initImageCache() {
        //计算可使用最大的内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //四分之一内存
        final int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mImageCache.put(HashKeyUtils.hashKeyFromUrl(url), bitmap);
    }

    @Override
    public Bitmap get(String url) {
        return mImageCache.get(HashKeyUtils.hashKeyFromUrl(url));
    }

    @Override
    public boolean clearAll() {
        mImageCache.evictAll();
        return true;
    }

    @Override
    public long cacheSize() {
        return mImageCache.size();
    }
}
