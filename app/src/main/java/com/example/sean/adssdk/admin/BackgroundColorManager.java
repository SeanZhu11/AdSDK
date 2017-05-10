package com.example.sean.adssdk.admin;

import android.content.Context;

import com.example.sean.adssdk.R;
import com.example.sean.adssdk.model.Consts;
import com.example.sean.adssdk.model.cache.ACache;
import com.example.sean.adssdk.controller.utils.DensityUtil;

/**
 * Created by sean on 2017/5/8.
 */

public class BackgroundColorManager {
    private final String TAG = BackgroundColorManager.class.getSimpleName();

    //均为16进制颜色值
    private int toolbarBackgroundColor;//toolbar背景颜色
    private int toolbarHeight;//toolbar高度

    private int toolbarTitleColor;//toolbar标题文字颜色
    private int toolbarTitleSize; //toolbar标题文字大小
    private String toolbarTitle; //toolbar的标题


    private int viewPagerTextSize; //Viewpager栏文字大小
    private int viewPagerTextSelectedColor;//Viewpager栏选中文字颜色
    private int viewPagerTextUnSelectedColor;//Viewpager栏未选中文字颜色

    private ACache aCache;
    private Context context;



    public BackgroundColorManager(Context context) {
        this.context = context;
        aCache = ACache.get(context);
        toolbarBackgroundColor = context.getResources().getColor(R.color.toolbar_bg);
        toolbarHeight = (int) context.getResources().getDimension(R.dimen.toolbar_height);

        toolbarTitleColor = context.getResources().getColor(R.color.white);
        toolbarTitleSize = (int) context.getResources().getDimension(R.dimen.toolbar_text_size)/2;
        toolbarTitle = "";

        viewPagerTextSize = (int) context.getResources().getDimension(R.dimen.viewpager_text_size)/2;
        viewPagerTextSelectedColor = context.getResources().getColor(R.color.viewpager_selected_color);
        viewPagerTextUnSelectedColor = context.getResources().getColor(R.color.viewpager_unselected_color);

    }

//    public void save(int toolbarBackgroundColor,
//                     int toolbarHeight,int toolbarTitleColor,
//                     int toolbarTitleSize,String toolbarTitle,
//                     int viewPagerTextSize,int viewPagerTextSelectedColor,
//                     int viewPagerTextUnSelectedColor){
//        setToolbarBackgroundColor(toolbarBackgroundColor);
//        setToolbarHeight(toolbarHeight);
//        setToolbarTitleColor(toolbarTitleColor);
//        setToolbarTitleSize(toolbarTitleSize);
//        setToolbarTitle(toolbarTitle);
//        setViewPagerTextSize(viewPagerTextSize);
//        setViewPagerTextSelectedColor(viewPagerTextSelectedColor);
//        setViewPagerTextUnSelectedColor(viewPagerTextUnSelectedColor);
//
//    }

    private void setToolbarBackgroundColor(int toolbarBackgroundColor){
        this.toolbarBackgroundColor = toolbarBackgroundColor;
        aCache.put(Consts.ToolbarBackgroundColor,String.valueOf(toolbarBackgroundColor));

    }
    public int getToolbarBackgroundColor(){
        if(aCache.getAsString(Consts.ToolbarBackgroundColor) != null){
            toolbarBackgroundColor
                    = Integer.valueOf(aCache.getAsString(Consts.ToolbarBackgroundColor));
        }
        return toolbarBackgroundColor;
    }



    private void setToolbarHeight(int toolbarHeight){
        this.toolbarHeight = toolbarHeight;
        aCache.put(Consts.ToolbarHeight,
                    String.valueOf(toolbarHeight));

    }
    public int getToolbarHeight(){
        if(aCache.getAsString(Consts.ToolbarHeight) != null){
            toolbarHeight
                    = Integer.valueOf(aCache.getAsString(Consts.ToolbarHeight));
        }
        return toolbarHeight;
    }



    private void setToolbarTitleColor(int toolbarTitleColor){
        this.toolbarTitleColor = toolbarTitleColor;
        aCache.put(Consts.ToolbarTitleColor,String.valueOf(toolbarTitleColor));
    }
    public int getToolbarTitleColor(){
        if(aCache.getAsString(Consts.ToolbarTitleColor) != null){
            toolbarTitleColor
                    = Integer.valueOf(aCache.getAsString(Consts.ToolbarTitleColor));
        }
        return toolbarTitleColor;
    }



    private void setToolbarTitleSize(int toolbarTitleSize){
        this.toolbarTitleSize = toolbarTitleSize;
        aCache.put(Consts.ToolbarTitleSize,String.valueOf(toolbarTitleSize));

    }
    public int getToolbarTitleSize(){
        if(aCache.getAsString(Consts.ToolbarTitleSize) != null){
            toolbarTitleSize =
                   Integer.valueOf(aCache.getAsString(Consts.ToolbarTitleSize));
        }
        return toolbarTitleSize;
    }

    private void setToolbarTitle(String toolbarTitle){
        this.toolbarTitle = toolbarTitle;
        aCache.put(Consts.ToolbarTitle,toolbarTitle);

    }
    public String getToolbarTitle(){
        if(aCache.getAsString(Consts.ToolbarTitle) != null){
            toolbarTitle =
                    aCache.getAsString(Consts.ToolbarTitle);
        }
        return toolbarTitle;
    }



    private void setViewPagerTextSize(int viewPagerTextSize){
        this.viewPagerTextSize = viewPagerTextSize;
        aCache.put(Consts.ViewPagerTextSize,String.valueOf(viewPagerTextSize));

    }
    public int getViewPagerTextSize(){
        if(aCache.getAsString(Consts.ViewPagerTextSize) != null){
            viewPagerTextSize
                    = Integer.valueOf(aCache.getAsString(Consts.ViewPagerTextSize));
        }
        return viewPagerTextSize;
    }



    private void setViewPagerTextSelectedColor(int viewPagerTextSelectedColor){
        this.viewPagerTextSelectedColor = viewPagerTextSelectedColor;
        aCache.put(Consts.ViewPagerTextSelectedColor,String.valueOf(viewPagerTextSelectedColor));
    }
    public int getViewPagerTextSelectedColor(){
        if(aCache.getAsString(Consts.ViewPagerTextSelectedColor) != null){
            viewPagerTextSelectedColor =
                    Integer.valueOf(aCache.getAsString(Consts.ViewPagerTextSelectedColor));
        }
        return viewPagerTextSelectedColor;
    }



    private void setViewPagerTextUnSelectedColor(int viewPagerTextUnSelectedColor){
        this.viewPagerTextUnSelectedColor = viewPagerTextUnSelectedColor;
        aCache.put(Consts.ViewPagerTextUnSelectedColor,
                    String.valueOf(viewPagerTextUnSelectedColor));
    }
    public int getViewPagerTextUnSelectedColor(){
        if(aCache.getAsString(Consts.ViewPagerTextUnSelectedColor) != null){
            viewPagerTextUnSelectedColor =
                    Integer.valueOf(aCache.getAsString(Consts.ViewPagerTextUnSelectedColor)
            );
        }
        return viewPagerTextUnSelectedColor;

    }


}
