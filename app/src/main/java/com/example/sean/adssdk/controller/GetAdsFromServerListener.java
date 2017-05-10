package com.example.sean.adssdk.controller;

/**
 * Created by sean on 2017/5/9.
 */

public interface GetAdsFromServerListener {
    void getAdsFromServerSuccessListener(Object object, boolean isNeededUpdateView);
    void getAdsFromServerFailedListener();
}
