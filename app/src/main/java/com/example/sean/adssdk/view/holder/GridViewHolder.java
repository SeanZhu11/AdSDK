package com.example.sean.adssdk.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sean.adssdk.R;

/**
 * Created by sean on 2017/5/2.
 */

public class GridViewHolder extends RecyclerView.ViewHolder {
    public ImageView adsIconAV;//appIcon
    public TextView adsNameTV;//广告名称
    public TextView adsDownloadTimesTV;//app下载次数
    public TextView adsDownloadTV;//下载是否免费
    public LinearLayout clickLinear;//整个大的Linear块点击区域


    public GridViewHolder(View view) {
        super(view);
        adsIconAV = (ImageView) view.findViewById(R.id.grid_app_icon);
        adsNameTV = (TextView) view.findViewById(R.id.grid_ads_title);
        adsDownloadTimesTV = (TextView) view.findViewById(R.id.grid_download_times_tv);
        adsDownloadTV = (TextView) view.findViewById(R.id.grid_action_tv);
        clickLinear = (LinearLayout) view.findViewById(R.id.grid_type_linear);

    }
}
