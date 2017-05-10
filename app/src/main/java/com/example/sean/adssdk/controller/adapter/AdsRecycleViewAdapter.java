package com.example.sean.adssdk.controller.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sean.adssdk.controller.utils.MLog;
import com.example.sean.adssdk.model.info.AdsInfo;
import com.example.sean.adssdk.model.Consts;
import com.example.sean.adssdk.R;
import com.example.sean.adssdk.view.holder.GridTitleHolder;
import com.example.sean.adssdk.view.holder.GridViewHolder;
import com.example.sean.adssdk.view.holder.MoreViewHolder;
import com.example.sean.adssdk.view.holder.TwoButtonViewHolder;
import com.example.sean.adssdk.view.holder.WallViewHolder;

import java.util.List;

/**
 * Created by Sean on 17/4/24.
 */

public class AdsRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = AdsRecycleViewAdapter.class.getSimpleName();


    private List<AdsInfo> adsList;
    private Context context;


    private LayoutInflater mInflater;

    private AdsInfo adsInfo;

    /**
     * @param adsList 需要展示的数据源
     */
    public AdsRecycleViewAdapter(List<AdsInfo> adsList, Context context) {
        this.adsList = adsList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }


    //点击后进入下载界面的监听
    public interface OnAdsDownloadListener {
        void onDownLoadClick(AdsInfo adsInfo);//跳转至应用商店

        void onMoreClick();//打开More界面

        void onCategoryClick();//打开分类界面

        void onListClick();//打开榜单界面

    }

    private OnAdsDownloadListener mListener;

    public void setOnAdsDownloadListener(OnAdsDownloadListener listener) {
        if (listener == null) {
            throw new NullPointerException();
        } else {
            this.mListener = listener;
        }

    }


    //


    @Override
    public int getItemCount() {
        return adsList.size();
    }


    @Override
    public int getItemViewType(int position) {
        MLog.d(TAG, "当前位置为 : " + position);
        adsInfo = adsList.get(position);
        return adsInfo.getAppShowType();//返回当前样式
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Consts.WallType:
                View wallView = mInflater.inflate(R.layout.type_wall, parent, false);
                final WallViewHolder wallViewHolder = new WallViewHolder(wallView);
                MLog.d(TAG,"viewHolder位置 ： "  + wallViewHolder.getAdapterPosition());
                MLog.d(TAG,"viewHolder上一个位置 ： "  + wallViewHolder.getOldPosition());
                wallViewHolder.clickRelative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adsInfo = adsList.get(wallViewHolder.getAdapterPosition());
                        mListener.onDownLoadClick(adsInfo);
                    }
                });

//                MLog.d(TAG, "viewType : " + viewType);
                return wallViewHolder;

            case Consts.GridType:
                View gridView = mInflater.inflate(R.layout.type_grid, parent, false);
                final GridViewHolder gridViewHolder = new GridViewHolder(gridView);
                gridViewHolder.clickLinear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adsInfo = adsList.get(gridViewHolder.getAdapterPosition());
                        mListener.onDownLoadClick(adsInfo);

                    }
                });

//                MLog.d(TAG, "viewType : " + viewType);
                return gridViewHolder;

            case Consts.GridTitle:
                View gridTitle = mInflater.inflate(R.layout.type_grid_title, parent, false);
                GridTitleHolder gridTitleHolder = new GridTitleHolder(gridTitle);

                gridTitleHolder.gridTitleLinear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onMoreClick();
                    }
                });
                return gridTitleHolder;


            case Consts.CategoryButtonType:
                View twoButton = mInflater.inflate(R.layout.type_two_button, parent, false);
                TwoButtonViewHolder twoButtonViewHolder = new TwoButtonViewHolder(twoButton);


                twoButtonViewHolder.categoryFrame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onCategoryClick();
                    }
                });


                twoButtonViewHolder.topFrame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onListClick();
                    }
                });
                return twoButtonViewHolder;
            default:
                return null;

        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int currentType = adsInfo.getAppShowType();
        String adsUrl = adsInfo.getAdsPicUrl();
        String appIcon = adsInfo.getAdsIcon();
        String adsName = adsInfo.getAdsTitle();
        float appRating = adsInfo.getRating();
        String appPackageSize = adsInfo.getAppSize();
        String appDownloadTimes = adsInfo.getAdsDownLoadCount();
        String adsActionText = adsInfo.getActionText();
        String adsIntroduction = adsInfo.getAdsDescription();
        //以下三个值为""时，赋给默认的值
        if (appPackageSize.equals("")) {
            appPackageSize = context.getString(R.string.empty_package_size);
        }
        if (adsActionText.equals("")) {
            adsActionText = context.getString(R.string.empty_costs);
        }
        if (appDownloadTimes.equals("")) {
            appDownloadTimes = context.getString(R.string.empty_download_times);
        }
        switch (currentType) {
            //广告墙样式
            case Consts.WallType:

                //广告图片
                glidePicsIntoView(adsUrl, ((WallViewHolder) holder).adsPicIV);
                //AppIcon
                glidePicsIntoView(appIcon, ((WallViewHolder) holder).adsIconAV);

                ((WallViewHolder) holder).adsNameTV.setText(adsName);
                ((WallViewHolder) holder).adsRatingStarBar.setStarMark(appRating);

                ((WallViewHolder) holder).adsPackageSizeTV.setText(appPackageSize);
                ((WallViewHolder) holder).adsDownloadTimesTV.setText(appDownloadTimes);
                ((WallViewHolder) holder).adsDownloadTV.setText(adsActionText);
                if (adsIntroduction.equals("")) {
                    ((WallViewHolder) holder).adsIntroductionTV.setVisibility(View.GONE);
                } else {
                    ((WallViewHolder) holder).adsIntroductionTV.setText(adsIntroduction);
                }


                break;

            //Grid样式的Header
            case Consts.GridTitle:


                break;
            //Grid样式
            case Consts.GridType:

                glidePicsIntoView(appIcon, ((GridViewHolder) holder).adsIconAV);

                ((GridViewHolder) holder).adsNameTV.setText(adsName);
                ((GridViewHolder) holder).adsDownloadTimesTV.setText(appDownloadTimes);
                ((GridViewHolder) holder).adsDownloadTV.setText(adsActionText);
                break;

            //更多栏样式
            case Consts.MoreType:
                glidePicsIntoView(appIcon, ((MoreViewHolder) holder).iconIV);

                ((MoreViewHolder) holder).adsTitleTV.setText(adsName);
                ((MoreViewHolder) holder).moreStarBar.setStarMark(appRating);
                ((MoreViewHolder) holder).packageSizeTV.setText(appPackageSize);
                ((MoreViewHolder) holder).adsDownloadTimesTV.setText(appDownloadTimes);
                ((MoreViewHolder) holder).moreDownloadTV.setText(adsActionText);
                break;

            //分类栏样式
            case Consts.CategoryButtonType:
                break;
        }


    }

    private void glidePicsIntoView(String url, ImageView imageView) {
        Glide
                .with(context)
                .load(url)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);

    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        //获取布局的manager
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case Consts.WallType:
                        case Consts.GridTitle:
                            return 6;
                        case Consts.GridType:
                            return 2;
                        case Consts.CategoryButtonType:
                            return 6;
                        default:
                            return 6;
                    }
                }
            });
        }
    }


}
