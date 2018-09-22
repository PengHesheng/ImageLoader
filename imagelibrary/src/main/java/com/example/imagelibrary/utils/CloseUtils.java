package com.example.imagelibrary.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author 14512 on 2018/9/22
 */
public final class CloseUtils {

    private CloseUtils() {

    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
