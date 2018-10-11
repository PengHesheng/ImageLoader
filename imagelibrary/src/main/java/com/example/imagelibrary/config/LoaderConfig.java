package com.example.imagelibrary.config;

import android.content.Context;

import com.example.imagelibrary.cache.DefaultCache;
import com.example.imagelibrary.cache.ImageCache;
import com.example.imagelibrary.loader.HttpLoader;
import com.example.imagelibrary.loader.HttpUrlConnectionLoader;

/**
 * @author 14512 on 2018/10/11
 */
public final class LoaderConfig {
    private Context mContext;
    private ImageCache mImageCache;
    private HttpLoader mHttpLoader;
    private int threadCount;

    private LoaderConfig(Context context) {
        mContext = context;
        mImageCache = new DefaultCache(context);
        mHttpLoader = new HttpUrlConnectionLoader();
        threadCount = Runtime.getRuntime().availableProcessors() + 1;
    }

    public ImageCache getImageCache() {
        return mImageCache;
    }

    public HttpLoader getHttpLoader() {
        return mHttpLoader;
    }

    public Context getContext() {
        return mContext;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public static class Builder {
        private Context mContext;
        private ImageCache mImageCache;
        private HttpLoader mHttpLoader;
        private int threadCount;

        public Builder(Context context) {
            mContext = context;
            mImageCache = new DefaultCache(context);
            mHttpLoader = new HttpUrlConnectionLoader();
            threadCount = Runtime.getRuntime().availableProcessors() + 1;
        }

        public Builder setCache(ImageCache imageCache) {
            this.mImageCache = imageCache;
            return this;
        }

        public Builder setHttpLoader(HttpLoader httpLoader) {
            this.mHttpLoader = httpLoader;
            return this;
        }

        public Builder setThreadCount(int threadCount) {
            this.threadCount = threadCount;
            return this;
        }

        public LoaderConfig build() {
            LoaderConfig config = new LoaderConfig(mContext);
            applyConfig(config);
            return config;
        }

        private void applyConfig(LoaderConfig config) {
            config.mImageCache = this.mImageCache;
            config.mHttpLoader = this.mHttpLoader;
            config.threadCount = this.threadCount;
        }
    }
}
