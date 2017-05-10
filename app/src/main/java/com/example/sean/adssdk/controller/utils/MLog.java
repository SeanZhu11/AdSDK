package com.example.sean.adssdk.controller.utils;

import android.util.Log;

/**
 * Created by Sean on 17/4/20.
 */

public class MLog {

    public static void v(String tag,String msg){
        Log.v("-----------" + tag + "-----------","   " + msg);
    }

    public static void d(String tag,String msg){
        Log.d("-----------" + tag + "-----------","   " + msg);
    }

    public static void i(String tag,String msg){
        Log.i("-----------" + tag + "-----------","   " + msg);
    }

    public static void w(String tag,String msg){
        Log.w("-----------" + tag + "-----------","   " + msg);
    }

    public static void e(String tag,String msg){
        Log.e("-----------" + tag + "-----------","   " + msg);
    }

}
