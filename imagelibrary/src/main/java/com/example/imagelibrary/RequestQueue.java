package com.example.imagelibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 14512 on 2018/9/22
 */
public class RequestQueue {

    private Queue<BitmapRequest> mQueue;

    /**
     * 线程池，数量为CPU
     */
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private int mThreadCount;

    public RequestQueue() {
        init();
    }

    private void init() {
        mQueue = new PriorityQueue<>();
    }

    public RequestQueue(int threadCount) {
        mThreadCount = threadCount;
        init();
    }

    private void submitLoadRequest(final String imgUrl, final ImageView imageView) {
        imageView.setTag(imgUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(imgUrl);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(imgUrl)) {
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(imgUrl, bitmap);
            }
        });
    }

    private Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void add(BitmapRequest request) {
        mQueue.add(request);
    }

    public void start() {
        while (true) {
            BitmapRequest request = mQueue.poll();
        }
    }
}
