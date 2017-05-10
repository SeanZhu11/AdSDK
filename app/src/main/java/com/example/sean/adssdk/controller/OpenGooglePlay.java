package com.example.sean.adssdk.controller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.example.sean.adssdk.R;
import com.example.sean.adssdk.controller.utils.MLog;

/**
 * Created by sean on 2017/5/9.
 */

public class OpenGooglePlay {

    private final  String TAG = OpenGooglePlay.class.getSimpleName();
    private final  String GooglePlayUrl = "com.android.vending";
    private final  String GooglePlayComponent =
            "com.google.android.finsky.activities.LaunchUrlHandlerActivity";



    public void openGooglePlay(String packageName, Context context) {

        //TODO 换成需要经过服务器转换的地址
        String packageBuffer = "market://details?id=" + packageName;
        try {
            Intent intent = new Intent();
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setAction(Intent.ACTION_MAIN);
            intent.setComponent(new ComponentName(GooglePlayUrl, GooglePlayComponent));
            intent.setData(Uri.parse(packageBuffer));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context,
                    context.getString(R.string.cant_open_googleplay),
                    Toast.LENGTH_SHORT).show();
            MLog.e(TAG, "openGooglePlay--" + e.toString());
        }
    }
}
