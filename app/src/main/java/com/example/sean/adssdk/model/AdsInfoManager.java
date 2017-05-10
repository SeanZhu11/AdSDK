package com.example.sean.adssdk.model;


import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.sean.adssdk.R;
import com.example.sean.adssdk.controller.utils.MLog;
import com.example.sean.adssdk.model.info.AdsInfo;
import com.example.sean.adssdk.model.info.MyAppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sean on 17/4/25.
 */

public class AdsInfoManager {
    private final String TAG = AdsInfoManager.class.getSimpleName();
    private Activity activity;


    public AdsInfoManager(Activity activity){
        this.activity = activity;
    }

    //读取Json数组
    public List<AdsInfo> AnalyseJson(JSONArray jsonArray) {
        List<AdsInfo>adsList = new ArrayList<>();
        if (jsonArray != null) {
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    AdsInfo adsInfo = getAdsInfos(jsonObject);
                    adsList.add(adsInfo);
                }
            } catch (JSONException e) {
                MLog.e(TAG, "initData : " + e.toString());
            }

        }
        //去除重复的应用
        adsList = getMyApps(activity,adsList);
        return adsList;
    }



    //扫描本机已经安装的App
    private  List<MyAppInfo> scanLocalInstallAppList(PackageManager packageManager) {

        List<MyAppInfo> myAppInfos = new ArrayList<>();
        try {
            List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
            for(PackageInfo packageInfo:packageInfos){
                //过滤掉系统app
                if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0) {
                    continue;
                }
                MyAppInfo myAppInfo = new MyAppInfo();
                myAppInfo.setAppName(packageInfo.packageName);
                myAppInfos.add(myAppInfo);
            }
        }catch (Exception e){
            MLog.e(TAG,"获取应用包信息失败");
        }
        return myAppInfos;
    }

    //去除本机已有的样应用
    private   List<AdsInfo> getMyApps(Activity activity,List<AdsInfo>adsList) {
        MLog.d(TAG, "开始扫描");
        List<MyAppInfo> myAppInfoList = scanLocalInstallAppList(
                activity.getPackageManager());

        List<AdsInfo> sameAppList = new ArrayList<>();

        for (MyAppInfo myAppInfo : myAppInfoList) {
            for (AdsInfo adsInfo : adsList) {
                if (adsInfo.getAdsBundleId().equals(myAppInfo.getAppName())) {
                    MLog.d("本地已有安装的程序的包名有 ： ", myAppInfo.getAppName());
                    sameAppList.add(adsInfo);
                }
            }
        }
        adsList.removeAll(sameAppList);
        MLog.d(TAG, "扫描完成");
        return adsList;

    }

    //解析Json文件
    private AdsInfo getAdsInfos(JSONObject jsonObject){
        AdsInfo adsInfo = new AdsInfo();
        try {
            String picUrl = jsonObject.getString(Consts.AdsPictureUrl);
            adsInfo.setAdsPicUrl(picUrl);
        }catch (Exception e){
            MLog.e(TAG,"JSONException" + "setPicUrl");
            adsInfo.setAdsPicUrl("");
        }

        try {
            String adsIcon = jsonObject.getString(Consts.AdsIcon);
            adsInfo.setAdsIcon(adsIcon);
        }catch (Exception e){
            MLog.e(TAG,"JSONException : " + "setAdsIcon");
            adsInfo.setAdsIcon("");
        }

        try {
            String adsIntroduction = jsonObject.getString(Consts.AdsDescription);
            adsInfo.setAdsDescription(adsIntroduction);
        }catch (Exception e){
            MLog.e(TAG,"JSONException : " + "setAdsDescription");
            adsInfo.setAdsDescription("");
        }

        try {
            String adsDownLoadUrl = jsonObject.getString(Consts.AdsUrl);
            adsInfo.setAdsUrl(adsDownLoadUrl);
        }catch (Exception e){
            MLog.e(TAG,"JSONException : " + "setAdsUrl");
            adsInfo.setAdsUrl("");
        }

        try {
            String appPackageName = jsonObject.getString(Consts.AdsBundleId);
            adsInfo.setAdsBundleId(appPackageName);
        }catch (Exception e){
            MLog.e(TAG,"JSONException" + "setAdsBundleId");
            adsInfo.setAdsBundleId("");
        }

        try {
            String appName = jsonObject.getString(Consts.AdsTitle);
            adsInfo.setAdsTitle(appName);
        }catch (Exception e){
            MLog.e(TAG,"JSONException" + "setAdsTitle");
            adsInfo.setAdsTitle("");
        }

        try {
            float adsRating =  Float.valueOf(jsonObject.getString(Consts.AdsRating));
            adsInfo.setRating(adsRating);
        }catch (Exception e){
            MLog.e(TAG,"JSONException" + "setRating");
            adsInfo.setRating(0);
        }

        try {
            String adsDownLoadTimes = jsonObject.getString(Consts.AdsDownLoadCount);
            adsInfo.setAdsDownLoadCount(adsDownLoadTimes);
        }catch (Exception e){
            MLog.e(TAG,"JSONException" + "setAdsDownLoadCount");
            adsInfo.setAdsDownLoadCount("");
        }

        try {
            String category = jsonObject.getString(Consts.Category);
            adsInfo.setCategory(category);
        }catch (Exception e){
            MLog.e(TAG,"JSONException" + "setCategory");
            adsInfo.setCategory("");
        }

        try {
            String appPackageSize = jsonObject.getString(Consts.AdsSize);
            adsInfo.setAppSize(appPackageSize);
        }catch (Exception e){
            MLog.e(TAG,"JSONException" + "setAppSize");
            adsInfo.setAppSize("");
        }

        try {
            String actionText = jsonObject.getString(Consts.ActionText);
            adsInfo.setActionText(actionText);
        }catch (Exception e){
            MLog.e(TAG,"JSONException" + "setActionText");
            adsInfo.setActionText(activity.getString(R.string.free));
        }

        return adsInfo;
    }




}
