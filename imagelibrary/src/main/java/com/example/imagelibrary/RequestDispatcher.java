package com.example.imagelibrary;

import android.util.Log;

import java.util.concurrent.BlockingDeque;

/**
 * @author 14512 on 2018/9/22
 */
final class RequestDispatcher extends Thread{

    private BlockingDeque<BitmapRequest> mRequestQueue;

    public RequestDispatcher(BlockingDeque<BitmapRequest> bitmapRequests) {
        mRequestQueue = bitmapRequests;
    }

    @Override
    public void run() {
        try {
            while (!this.isInterrupted()) {
                final BitmapRequest request = mRequestQueue.take();
                if (request.isCancel) {
                    continue;
                }
                final String schema = parseSchema(request.url);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String parseSchema(String url) {
        if (url.contains("://")) {
            return url.split("://")[0];
        } else {
            Log.e(getName(), "### wrong scheme, image uri is : " + url);
        }

        return "";
    }
}
