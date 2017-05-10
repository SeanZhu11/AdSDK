package com.example.sean.adssdk.controller.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sean.adssdk.R;
import com.example.sean.adssdk.model.cache.ACache;
import com.example.sean.adssdk.model.Consts;
import com.example.sean.adssdk.controller.utils.MLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestActivity extends BaseActivity {
    private final String TAG = TestActivity.class.getSimpleName();
    private Button intentButton;
    private Button preloadButton;
    private Context context;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initView();
        setOnclickListner();
        getJsonTest();
    }


    private void getJsonTest() {
        try {
            InputStream in = context.getAssets().open("testJson.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder stringBuilder = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
            }
            ACache aCache = ACache.get(context);
            JSONObject jsonObject = new JSONObject(String.valueOf(stringBuilder));
            try {
                JSONArray dataArrary = jsonObject.getJSONArray(Consts.Data);
                aCache.put(Consts.Data, dataArrary);
            } catch (JSONException e) {
                MLog.e(TAG + "data ： ", e.toString());
            }
            try {
                JSONArray applicationArrary = jsonObject.getJSONArray(Consts.Application);
                aCache.put(Consts.Application, applicationArrary);
            } catch (JSONException e) {
                MLog.e(TAG + "application ： ", e.toString());
            }
            try {
                JSONArray gameArrary = jsonObject.getJSONArray(Consts.Game);
                aCache.put(Consts.Game, gameArrary);

            } catch (JSONException e) {
                MLog.e(TAG + "game ： ", e.toString());
            }

        } catch (Exception e) {
            MLog.e(TAG, e.toString());
        }

    }


    private void initView() {
        intentButton = (Button) findViewById(R.id.intent_button);
        preloadButton = (Button) findViewById(R.id.preload_button);
    }

    private void setOnclickListner() {
        intentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectActivity.start(context);
            }
        });

        preloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<AdsInfo> urlList = new ArrayList<>();
//                for (String url : Consts.ImageThumbUrls) {
//                    AdsInfo adsInfoManager = new AdsInfo();
//                    adsInfoManager.setAdsPicUrl(url);
//                    adsInfoManager.setAdsIcon(url);
//                    urlList.add(adsInfoManager);
//                }
//                LoadPics.preLoadPics(urlList, context);

            }
        });
    }
}
