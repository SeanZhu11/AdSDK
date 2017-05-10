package com.example.sean.adssdk.model.info;


import android.content.Context;
import android.telephony.TelephonyManager;

import com.example.sean.adssdk.model.cache.ACache;
import com.example.sean.adssdk.model.Consts;
import com.example.sean.adssdk.controller.utils.MLog;

import java.util.Locale;

/**
 * Created by sean on 2017/4/26.
 * 向服务器发送请求时应该提交的数据
 */

public class RequestInfo {
    private final String TAG = RequestInfo.class.getSimpleName();

    private String countryCode;
    private String deviceID;
    private String appID;
    private String userID;

    private ACache aCache;
    private Context context;

    public RequestInfo(Context context){
        this.context = context;
        aCache = ACache.get(context);
        initRequestInfo();
    }

    private void initRequestInfo(){
        countryCode = Locale.getDefault().getCountry().toUpperCase();
        aCache.put(Consts.CountryCode,countryCode);
        try {
            TelephonyManager telephonyManager  = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceID = telephonyManager.getDeviceId();
        }catch (Exception e){
            MLog.e(TAG,e.toString());
            deviceID = "";
        }
        aCache.put(Consts.DeviceID,deviceID);
        appID = "";
        aCache.put(Consts.AppID,appID);
        userID = "";
        aCache.put(Consts.UserID,userID);
    }

    public void setCountryCode(String countryCode){
        this.countryCode = countryCode;
        aCache.put(Consts.CountryCode,countryCode);
    }
    public String getCountryCode(){
        return aCache.getAsString(Consts.CountryCode);
    }


    public void setDeviceID(String deviceID){
        this.deviceID = deviceID;
        aCache.put(Consts.DeviceID,deviceID);
    }
    public String getDeviceID(){
        return aCache.getAsString(Consts.DeviceID);
    }


    public void setAppID(String appID){
        this.appID = appID;
        aCache.put(Consts.AppID,appID);
    }
    public String getAppID(){
        return aCache.getAsString(Consts.AppID);
    }


    public void setUserID(String userID){
        this.userID = userID;
        aCache.put(Consts.UserID,userID);
    }
    public String getUserID(){
         return aCache.getAsString(Consts.UserID);
    }



}
