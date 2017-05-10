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

public class MoreViewHolder  extends RecyclerView.ViewHolder{
    public RelativeLayout moreRela;
    public ImageView iconIV;
    public TextView adsTitleTV;
    public TextView packageSizeTV;
    public TextView adsDownloadTimesTV;
    public StarBar moreStarBar;
    public TextView moreDownloadTV;
    public TextView moreIntroductionTV;
    public MoreViewHolder(View view){
        super(view);
        moreRela = (RelativeLayout) view.findViewById(R.id.more_type_rela);
        iconIV = (ImageView) view.findViewById(R.id.more_icon);
        adsTitleTV = (TextView) view.findViewById(R.id.more_ads_title);
        packageSizeTV = (TextView) view.findViewById(R.id.package_size_tv);
        adsDownloadTimesTV = (TextView) view.findViewById(R.id.ads_download_times_tv);
        moreStarBar = (StarBar) view.findViewById(R.id.more_starbar);
        moreDownloadTV = (TextView) view.findViewById(R.id.more_action_tv);
        moreIntroductionTV = (TextView) view.findViewById(R.id.more_introduction);


    }
}
