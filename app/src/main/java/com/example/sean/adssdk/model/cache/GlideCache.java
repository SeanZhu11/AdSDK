package com.example.sean.adssdk.model.cache;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by Sean on 17/4/25.
 */

public class GlideCache implements GlideModule{

    @Override
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        //ARGB_8888格式：指图片大小为32bit
        glideBuilder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
        //设置磁盘缓存目录（和创建的缓存目录相同）
        File storageDirectory = Environment.getExternalStorageDirectory();
        String downloadDirectoryPath = storageDirectory+"/GlideCache";
        //设置缓存的大小为100M
        int cacheSize = 100*1000*1000;
        glideBuilder.
                setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, cacheSize));

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
