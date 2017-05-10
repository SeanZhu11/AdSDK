package com.example.sean.adssdk.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sean.adssdk.R;
import com.example.sean.adssdk.model.info.AdsInfo;
import com.example.sean.adssdk.model.Consts;
import com.example.sean.adssdk.controller.utils.MLog;
import com.example.sean.adssdk.view.holder.MorePicHolder;
import com.example.sean.adssdk.view.holder.MoreViewHolder;

import java.util.List;

/**
 * Created by sean on 2017/5/3.
 */

public class MoreRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = MoreRecycleViewAdapter.class.getSimpleName();


    private List<AdsInfo> adsList;


    private AdsInfo adsInfo;

    /**
     * @param adsList 数据源
     */
    public MoreRecycleViewAdapter(List<AdsInfo> adsList) {
        this.adsList = adsList;


    }

    private interface onItemClick {
        void onItemClick(String downloadUrl);
    }

    private onItemClick onItemClick;

    public void setOnItemClickListener(onItemClick listener) {
        this.onItemClick = listener;

    }

    @Override
    public int getItemCount() {
        return adsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        adsInfo = adsList.get(position);
        return adsInfo.getAppShowType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case Consts.MoreType:
                View moreView = mInflater.inflate(R.layout.type_more, parent, false);
                MoreViewHolder moreViewHolder = new MoreViewHolder(moreView);

                moreViewHolder.moreRela.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClick != null) {
                            onItemClick.onItemClick(adsInfo.getAdsUrl());
                        } else {
                            MLog.e(TAG, "onClick : " + "mListener is null");
                        }
                    }
                });
                return moreViewHolder;
            //更多界面的大图展示
            case Consts.MorePic:
                MLog.d(TAG,"当前展示的类型为的 ： MorePic");
                View morePicView = mInflater.inflate(R.layout.type_more_pic,parent,false);
                MorePicHolder morePicHolder = new MorePicHolder(morePicView);
                //TODO:相应的处理事件

                return morePicHolder;

            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        String picUrl = adsInfo.getAdsPicUrl();
        String adsIconUrl = adsInfo.getAdsIcon();
        String adsName = adsInfo.getAdsTitle();
        float  appRating = adsInfo.getRating();
        String appPackageSize = adsInfo.getAppSize();
        String appDownloadTimes = adsInfo.getAdsDownLoadCount();
        String appDownloadCosts = adsInfo.getActionText();
        String adsIntroduction = adsInfo.getAdsDescription();
        //以下三个值为""时，赋给默认的值
        if (appPackageSize.equals("")) {
            appPackageSize = context.getString(R.string.empty_package_size);
        }
        if (appDownloadCosts.equals("")) {
            appDownloadCosts = context.getString(R.string.empty_costs);
        }
        if (appDownloadTimes.equals("")) {
            appDownloadTimes = context.getString(R.string.empty_download_times);
        }
        if(holder instanceof MoreViewHolder){
            //AppIcon
            Glide
                    .with(context)
                    .load(adsIconUrl)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(((MoreViewHolder) holder).iconIV);

            ((MoreViewHolder) holder).adsTitleTV.setText(adsName);
            ((MoreViewHolder) holder).moreStarBar.setStarMark(appRating);
            ((MoreViewHolder) holder).packageSizeTV.setText(appPackageSize);
            ((MoreViewHolder) holder).adsDownloadTimesTV.setText(appDownloadTimes);
            ((MoreViewHolder) holder).moreDownloadTV.setText(appDownloadCosts);
            if(adsIntroduction.equals("")){
                ((MoreViewHolder) holder).moreIntroductionTV.setVisibility(View.GONE);

            }else {
                ((MoreViewHolder) holder).moreIntroductionTV.setText(adsIntroduction);
            }


        }

        //大图展示
        if(holder instanceof MorePicHolder){
            if(picUrl != null && !picUrl.equals("")){
                Glide
                        .with(context)
                        .load(picUrl)
                        .skipMemoryCache(false)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(((MorePicHolder) holder).morePicIV);
            }
            else {
                MLog.e(TAG,"传入的大图URL为空");
            }

        }


    }


}

