package com.example.imagelibrary.loader;

import com.example.imagelibrary.BitmapRequest;

/**
 * @author 14512 on 2018/9/22
 */
public interface Loader {

    /**
     * @param result
     */
    void loadImage(BitmapRequest result);
}
