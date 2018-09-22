package com.example.imagelibrary.config;

import com.example.imagelibrary.loader.LoadPolicy;
import com.example.imagelibrary.cache.DefaultCache;
import com.example.imagelibrary.cache.ImageCache;

/**
 * @author 14512 on 2018/9/22
 */
public class ImageLoaderConfig {

    public ImageCache imageCache = new DefaultCache();
    public DisplayConfig displayConfig = new DisplayConfig();
    public LoadPolicy policy;
    public int threadCount = Runtime.getRuntime().availableProcessors() + 1;

    private ImageLoaderConfig() {

    }


    public static class Builder {
        ImageCache mImageCache = new DefaultCache();
        DisplayConfig mDisplayConfig = new DisplayConfig();
        LoadPolicy mLoadPolicy;
        int threadCount = Runtime.getRuntime().availableProcessors() + 1;

        public Builder setThreadCount(int count) {
            threadCount = count;
            return this;
        }

        public Builder setCache(ImageCache imageCache) {
            mImageCache = imageCache;
            return this;
        }

        public Builder setLoadingPlaceholder(int resId) {
            mDisplayConfig.loadingResId = resId;
            return this;
        }

        public Builder setFiledPlaceholder(int resId) {
            mDisplayConfig.failedResId = resId;
            return this;
        }

        public Builder setLoadPolicy(LoadPolicy loadPolicy) {
            if (loadPolicy != null) {
                mLoadPolicy = loadPolicy;
            }
            return this;
        }

        void applyConfig(ImageLoaderConfig config) {
            config.imageCache = this.mImageCache;
            config.displayConfig = this.mDisplayConfig;
            config.policy = this.mLoadPolicy;
            config.threadCount = this.threadCount;
        }

        public ImageLoaderConfig create() {
            ImageLoaderConfig config = new ImageLoaderConfig();
            applyConfig(config);
            return config;
        }
    }

}
