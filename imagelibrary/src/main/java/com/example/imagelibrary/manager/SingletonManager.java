package com.example.imagelibrary.manager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 14512 on 2018/10/12
 */
final class SingletonManager {
    public static final String DISPLAY_MANAGER = "DisplayManager";
    public static final String LOADER_MANAGER = "LoaderManager";
    public static final String REQUEST_MANAGER = "RequestManager";
    private static Map<String, Object> sMap = new HashMap<>();

    private SingletonManager() {

    }

    static void registerService(String key, Object instance) {
        if (!sMap.containsKey(key)) {
            sMap.put(key, instance);
        }
    }

    static Object getService(String key) {
        return sMap.get(key);
    }
}
