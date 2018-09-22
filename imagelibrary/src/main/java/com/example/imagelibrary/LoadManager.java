package com.example.imagelibrary;

import com.example.imagelibrary.loader.Loader;
import com.example.imagelibrary.loader.LocalLoader;
import com.example.imagelibrary.loader.UrlLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 14512 on 2018/9/22
 */
public class LoadManager {
    private LoadManager mInstance;

    public static final String HTTP = "http";
    public static final String HTTPS = "https";
    public static final String FILE = "file";

    /**
     *
     */
    private Map<String, Loader> mLoaderMap = new HashMap<String, Loader>();

    private Loader mNullLoader = new NullLoader();
    /**
     *
     */
    private static LoadManager INSTANCE;

    private LoadManager() {
        register(HTTP, new UrlLoader());
        register(HTTPS, new UrlLoader());
        register(FILE, new LocalLoader());
    }

    public LoadManager getInstance() {
        if (mInstance == null) {
            synchronized (LoadManager.class) {
                if (mInstance == null) {
                    mInstance = new LoadManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * @param schema
     * @param loader
     */
    public final synchronized void register(String schema, Loader loader) {
        mLoaderMap.put(schema, loader);
    }

    public Loader getLoader(String schema) {
        if (mLoaderMap.containsKey(schema)) {
            return mLoaderMap.get(schema);
        }
        return mNullLoader;
    }
}
