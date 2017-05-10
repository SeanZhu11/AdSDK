package com.example.sean.adssdk.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sean.adssdk.R;
import com.example.sean.adssdk.view.widget.StarBar;

/**
 * Created by sean on 2017/5/2.
 */

public class WallViewHolder extends RecyclerView.ViewHolder {
    public ImageView adsPicIV;//广告大图
    public ImageView adsIconAV;//appIcon
    public TextView adsNameTV;//广告名称
    public StarBar adsRatingStarBar;//广告评分
    public TextView adsPackageSizeTV;//app下载的包大小
    public TextView adsDownloadTimesTV;//app下载次数
    public TextView adsDownloadTV;//下载是否免费
    public TextView adsIntroductionTV;//广告介绍

    public RelativeLayout clickRelative;//整个大的Linear块点击区域


    public WallViewHolder(View view) {
        super(view);
        adsPicIV = (ImageView) view.findViewById(R.id.ads_list_iv);
        adsIconAV = (ImageView) view.findViewById(R.id.app_icon_av);
        adsNameTV = (TextView) view.findViewById(R.id.ads_name_tv);
        adsRatingStarBar = (StarBar) view.findViewById(R.id.ads_rating_star);
        adsPackageSizeTV = (TextView) view.findViewById(R.id.package_size_tv);
        adsDownloadTimesTV = (TextView) view.findViewById(R.id.ads_download_times_tv);
        adsDownloadTV = (TextView) view.findViewById(R.id.ads_action_tv);
        adsIntroductionTV = (TextView) view.findViewById(R.id.ads_introduction_tv);
        clickRelative = (RelativeLayout) view.findViewById(R.id.ads_rela);

    }
}