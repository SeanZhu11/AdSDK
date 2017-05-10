package com.example.sean.adssdk.admin;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.sean.adssdk.controller.GetAdsFromServer;
import com.example.sean.adssdk.controller.utils.MLog;
import com.example.sean.adssdk.model.Consts;
import com.example.sean.adssdk.model.info.AdsInfo;

import java.util.List;

/**
 * Created by Sean on 17/4/25.
 */

public class LoadPics {

    private final String TAG = LoadPics.class.getSimpleName();
    private Context context;

    public LoadPics(Context context){
        this.context = context;

    }

    //图片预加载
    public void preLoadPics() {

        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case Consts.Success:
                        Object obj = msg.obj;
                        //TODO:分析body
//                        if (adsInfoList != null) {
//                            int size = adsInfoList.size();
//                            //只有20张图片时，全部加载
//                            if (size < 20) {
//                                MLog.d(TAG, "当前链接长度为 ：" + size);
//                            }
//                            //超过20张图片时，预加载只加载前20张图片
//                            if (size >= 20) {
//                                MLog.d(TAG, "当前链接长度大于20");
//                                size = 20;
//                            }
//                            for (int i = 0 ;i < size ;i++){
//                                //下载广告大图
//                                loadPic(adsInfoList.get(i).getAdsPicUrl(),context);
//                                //下载广告的图标
//                                loadPic(adsInfoList.get(i).getAdsIcon(),context);
//                            }
//
//                        }
//                        else{
//                            MLog.e(TAG, "传入的UrlList为空");
//                        }

                        break;
                    case Consts.Fail:
                        MLog.e(TAG,"预加载时网络请求出错");
                        break;
                }
                return false;
            }
        });

    }

    private  void loadPic(String ImageUrl){
            if (ImageUrl != null && !ImageUrl.equals("")) {
                Glide
                        .with(context)
                        .load(ImageUrl)
                        .skipMemoryCache(false)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                MLog.e(TAG, "加载链接为 ：" + model);
                                MLog.e(TAG, "异常原因为：" + e.toString());
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                MLog.d(TAG, "加载链接为 ：" + model);
                                MLog.d(TAG, "isFirstResource : " + isFirstResource);
                                MLog.d(TAG, "isFromMemoryCache : " + isFromMemoryCache);
                                return false;
                            }
                        })
                        .preload();
            } else {
                MLog.e(TAG, "preLoadPics : " + "传入的URL为空");
                throw new NullPointerException();
            }

    }


}
