package com.example.imagelibrary.cache;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.imagelibrary.utils.CloseUtils;
import com.example.imagelibrary.utils.HashKeyUtils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * 物理设备缓存
 * @author 14512 on 2018/9/22
 */
class DiskCache implements ImageCache {
    private static final String CACHE_DIR = "bitmap";
    /**
     * 50M
     */
    private static final long DISK_CACHE_SIZE = 1024 * 1024 * 50;
    private static final int DISK_CACHE_INDEX = 0;
    private Context mContext;
    private File mCacheDir;
    private DiskLruCache mDiskLruCache;

    DiskCache(Context context) {
        mContext = context;
        mCacheDir = getCacheDir();
        try {
            mDiskLruCache = DiskLruCache.open(mCacheDir, 1, 1, DISK_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(String url, Bitmap bitmap) {
//        selfCache(url, bitmap);
        diskLruCache(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = null;
        String key = HashKeyUtils.hashKeyFromUrl(url);
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
            if (snapshot != null) {
                FileInputStream fis = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
                FileDescriptor descriptor = fis.getFD();
                bitmap = BitmapFactory.decodeFileDescriptor(descriptor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
        //采用直接写入的时候使用该返回值
//        return BitmapFactory.decodeFile(CACHE_DIR + url);
    }

    @Override
    public boolean clearAll() {
        return true;
    }

    @Override
    public long cacheSize() {
        return mDiskLruCache.size();
    }

    /**
     * 使用DiskLruCache缓存
     * @param url
     * @param bitmap
     */
    private void diskLruCache(String url, Bitmap bitmap) {
        if (!mCacheDir.exists()) {
            if (!mCacheDir.mkdirs()) {
                return;
            }
        }
        OutputStream os = null;
        try {
            String key = HashKeyUtils.hashKeyFromUrl(url);
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
            if (editor != null) {
                os = editor.newOutputStream(DISK_CACHE_INDEX);
                if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)) {
                    editor.commit();
                } else {
                    editor.abort();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeQuietly(os);
        }
    }

    /**
     * 获取缓存文件
     * @return
     */
    @SuppressLint("NewApi")
    private File getCacheDir() {
        boolean externalStorageAvailable = Objects.equals(Environment.getExternalStorageState(),
                Environment.MEDIA_MOUNTED);
        final String cachePath;
        if (externalStorageAvailable) {
            cachePath = Objects.requireNonNull(mContext.getExternalCacheDir()).getPath();
        } else {
            cachePath = mContext.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + CACHE_DIR);
    }

    /**
     * 直接写入文件
     * @param url
     * @param bitmap
     */
    private void selfCache(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(mCacheDir.getPath() + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeQuietly(fileOutputStream);
        }
    }


}
