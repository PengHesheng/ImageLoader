package com.example.imagelibrary.manager;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.imagelibrary.data.LoaderResult;
import com.example.imagelibrary.cache.ImageCache;
import com.example.imagelibrary.data.Task;
import com.example.imagelibrary.loader.HttpLoader;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 14512 on 2018/10/10
 */
public enum  HttpManager {
    /**
     * 枚举单例
     */
    INSTANCE;
    private static final String TAG = "HttpManager";
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POLL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE = 1;
    private static final BlockingQueue<Runnable> BLOCKING_QUEUE = new LinkedBlockingQueue<>(128);
    private static final ThreadFactory FACTORY = new ThreadFactory() {
        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r);
        }
    };
    private static final Executor EXECUTOR_POOL = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAXIMUM_POLL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, BLOCKING_QUEUE, FACTORY);

    private Handler mHandler;
    public void download(Task task) {
        mHandler = task.getHandler();
        //TODO 线程池根据传进的参数而改变？？？
        EXECUTOR_POOL.execute(new DownloadThread(task));
    }

    private class DownloadThread implements Runnable {
        private Task mTask;

        DownloadThread(Task task) {
            this.mTask = task;
        }

        @Override
        public void run() {
            ImageCache cache = mTask.getConfiguration().getImageCache();
            LoaderResult result = mTask.getLoaderResult();
            String url = (String) result.getUrl();
            Bitmap bitmap = cache.get(url);
            if (bitmap != null) {
                result.mBitmap = bitmap;
                mHandler.obtainMessage(LoaderResult.RESULT_SUCCESS_FROM_HTTP, result).sendToTarget();
                return;
            }
            HttpLoader loader = mTask.getConfiguration().getHttpLoader();
            bitmap = loader.download(url);
            if (bitmap != null) {
                cache.put(url, bitmap);
            }
            result.mBitmap = bitmap;
            Log.d(TAG, "bitmap=" + result.mBitmap + " url=" + url);
            mHandler.obtainMessage(LoaderResult.RESULT_SUCCESS_FROM_HTTP, result).sendToTarget();
        }
    }

}
