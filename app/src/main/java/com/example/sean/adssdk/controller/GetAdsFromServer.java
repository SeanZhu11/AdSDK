package com.example.sean.adssdk.controller;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.sean.adssdk.controller.utils.MLog;
import com.example.sean.adssdk.model.Consts;
import com.example.sean.adssdk.model.info.RequestInfo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by sean on 2017/4/26.
 */

public class GetAdsFromServer {

    private final  String TAG = GetAdsFromServer.class.getSimpleName();

    /**
     * @param context
     * @param handler
     */
    private void postFromServer(final Context context,final Handler handler) {
        String url = Consts.RequestBaseURL;
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        //获取提交给服务器的信息
        RequestInfo requestInfo = new RequestInfo(context);
        String countryCode = requestInfo.getCountryCode();
        String deviceID = requestInfo.getDeviceID();
        String useID = requestInfo.getUserID();
        String appID = requestInfo.getAppID();


        //post请求来获得数据
        //创建一个RequestBody，存放重要数据的键值对
        RequestBody body = new FormBody.Builder()
                .add(Consts.CountryCode, countryCode)
                .add(Consts.DeviceID, deviceID)
                .add(Consts.UserID, useID)
                .add(Consts.AppID, appID)
                .build();
        //创建一个请求对象，传入URL地址和相关数据的键值对的对象
        final Request request = new Request.Builder()
                .url(url)
                .post(body).build();

        //创建一个能处理请求数据的操作类
        Call call = client.newCall(request);

        //使用异步任务的模式请求数据
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MLog.e(TAG, "网络请求失败，失败原因为 ：" + e.toString());
                Message message = new Message();
                message.what = Consts.Fail;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MLog.d(TAG, response.body().string());
                Message message = new Message();
                message.what = Consts.Success;
                message.obj = response.body();
                handler.sendMessage(message);

            }
        });

    }


    public void getAds(final Context context,final boolean isNeededUpdateView,
                              final GetAdsFromServerListener getAdsFromServerListener) {
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                //接受消息，进行处理
                switch (msg.what) {
                    case Consts.Success:
                        MLog.d(TAG, "网络请求成功");
                        getAdsFromServerListener.getAdsFromServerSuccessListener(msg.obj,isNeededUpdateView);
                        break;
                    case Consts.Fail:
                        MLog.e(TAG, "网络请求失败");
                        getAdsFromServerListener.getAdsFromServerFailedListener();
                        break;
                }
                return true;
            }
        });
        postFromServer(context, handler);
    }


}
