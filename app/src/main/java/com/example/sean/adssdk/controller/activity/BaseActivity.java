package com.example.sean.adssdk.controller.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sean.adssdk.R;
import com.example.sean.adssdk.admin.BackgroundColorManager;
import com.example.sean.adssdk.controller.utils.MLog;

public abstract class BaseActivity extends AppCompatActivity {

    private  final String TAG = BaseActivity.class.getSimpleName();


    private ImageView mToolbarBackIV;
    private BackgroundColorManager backgroundColorManager;



    /**
     * 设置layout布局,在子类重写该方法.
     *
     * @return res layout xml id
     */
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        backgroundColorManager = new BackgroundColorManager(this);
//        aCache = ACache.get(this);

        //初始化ToolBar
        initToolBar();

    }

    private void initToolBar() {
        Toolbar mToolbar;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            int toolbarBackgroundColor =
                    backgroundColorManager.getToolbarBackgroundColor();
            mToolbar.setBackgroundColor(toolbarBackgroundColor);
            setSupportActionBar(mToolbar);
        }

        initToolBarTitle();
    }


    private void initToolBarTitle() {
        TextView mToolbarTitle;
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        if (mToolbarTitle != null) {
            String toolbarTitle = backgroundColorManager.getToolbarTitle();
            int toolbarTitleColor = backgroundColorManager.getToolbarTitleColor();
            int toolbarTitleSize = backgroundColorManager.getToolbarTitleSize();
            //通过设置TextView的高度来间接控制ToolBar的高度
            int toolbarHeight = backgroundColorManager.getToolbarHeight();
            if(!toolbarTitle.equals("")){
                mToolbarTitle.setText(toolbarTitle);
            }else {
                mToolbarTitle.setText(getTitle());
            }

            mToolbarTitle.setTextColor(toolbarTitleColor);
            mToolbarTitle.setTextSize(toolbarTitleSize);
            mToolbarTitle.setHeight(toolbarHeight);
        }

        //设置默认的标题不显示
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

    }




    @Override
    protected void onStart() {
        super.onStart();
        if (null != getToolbar() && isShowBacking()) {
            showBack(R.mipmap.back);
        }
    }


    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }


    private void showBack(int resourceId) {
        mToolbarBackIV = (ImageView) findViewById(R.id.toolbar_back);
        mToolbarBackIV.setImageResource(resourceId);
        mToolbarBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 是否显示后退按钮,默认不显示,可在子类重写该方法.
     *
     * @return
     */
    protected boolean isShowBacking() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MLog.d(TAG, "onDestroy...");


    }
}
