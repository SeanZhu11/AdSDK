package com.example.sean.adssdk.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sean.adssdk.R;
import com.example.sean.adssdk.controller.GetAdsFromServer;
import com.example.sean.adssdk.controller.GetAdsFromServerListener;
import com.example.sean.adssdk.controller.OpenGooglePlay;
import com.example.sean.adssdk.controller.activity.MoreActivity;
import com.example.sean.adssdk.model.cache.ACache;
import com.example.sean.adssdk.model.info.AdsInfo;
import com.example.sean.adssdk.model.Consts;
import com.example.sean.adssdk.model.AdsInfoManager;
import com.example.sean.adssdk.controller.utils.MLog;
import com.example.sean.adssdk.controller.adapter.AdsRecycleViewAdapter;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sean on 17/4/25.
 */

public class GamesFragment extends Fragment {

    private final String TAG = GamesFragment.class.getSimpleName();


    private AdsRecycleViewAdapter adsRecycleViewAdapter;
    private List<AdsInfo> adsList;
    private Context context;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_featured, container, false);
        context = getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.main_recycleview);
        gridLayoutManager = new GridLayoutManager(
                context, 6, GridLayoutManager.VERTICAL, false);


        //初始化广告数据
        initData();

        return view;
    }

    private void initView() {
        initRecycleView();
        setOnclickListener();
    }


    GetAdsFromServerListener getAdsFromServerListener = new GetAdsFromServerListener() {
        @Override
        public void getAdsFromServerSuccessListener(Object object, boolean isNeededUpdateView) {
            //TODO 相关消息处理
            //TODO 更新缓存
            //TODO 相关的视图处理
        }

        @Override
        public void getAdsFromServerFailedListener() {

        }
    };

    private void initData() {
        adsList = new ArrayList<>();
        ACache aCache = ACache.get(context);
        //TODO 此处改为Games
        JSONArray dataArray = aCache.getAsJSONArray(Consts.Data);
        AdsInfoManager adsInfoManager = new AdsInfoManager(getActivity());
        GetAdsFromServer getAdsFromServer = new GetAdsFromServer();
        //当缓存中有数据时，先读取缓存
        if (dataArray != null) {
            adsList = adsInfoManager.AnalyseJson(dataArray);
            initView();
            //TODO 显示的同时在后台刷新数据
//            getAdsFromServer.getAds(context, false, getAdsFromServerListener);
        } else {
            MLog.d(TAG, "当前缓存中无数据");
//            getAdsFromServer.getAds(context, true, getAdsFromServerListener);

        }
    }

    private void initRecycleView() {
        MLog.d(TAG, "initRecycleView");
        adsRecycleViewAdapter = new AdsRecycleViewAdapter(adsList, context);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adsRecycleViewAdapter);
    }


    private void setOnclickListener() {
        if (adsRecycleViewAdapter != null) {
            adsRecycleViewAdapter.setOnAdsDownloadListener(new AdsRecycleViewAdapter.OnAdsDownloadListener() {
                @Override
                public void onDownLoadClick(AdsInfo adsInfo) {
                    MLog.d(TAG, adsInfo.getAdsBundleId());
                    OpenGooglePlay openGooglePlay = new OpenGooglePlay();
                    openGooglePlay.openGooglePlay(adsInfo.getAdsBundleId(),context);
                }
                @Override
                public void onMoreClick() {
                    MoreActivity.start(context);
                }

                @Override
                public void onCategoryClick() {
//                CategoryActivity.start(context);
                }

                @Override
                public void onListClick() {
//                TopActivity.start(context);
                }
            });

        } else {
            MLog.e(TAG, "adsRecycleViewAdapter is null");
        }

    }


}


