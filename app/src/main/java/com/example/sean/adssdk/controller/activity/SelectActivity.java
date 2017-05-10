package com.example.sean.adssdk.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sean.adssdk.R;
import com.example.sean.adssdk.admin.BackgroundColorManager;
import com.example.sean.adssdk.controller.adapter.MFragmentPagerAdapter;
import com.example.sean.adssdk.controller.fragment.AppsFragment;
import com.example.sean.adssdk.controller.fragment.FeaturedFragment;
import com.example.sean.adssdk.controller.fragment.GamesFragment;

import java.util.ArrayList;

public class SelectActivity extends BaseActivity {

    private final String TAG = SelectActivity.class.getSimpleName();

    //精选
    private TextView featuredTextView;
    //游戏
    private TextView gamesTextView;
    //应用
    private TextView appsTextView;


    //实现Tab滑动效果
    private ViewPager mViewPager;

    //动画图片
    private ImageView cursor;

    //动画图片偏移量
    private int offset = 0;
    private int position_one;
    private int position_two;


    //当前页卡编号
    private int currIndex = 0;

    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;

    //管理Fragment
    private FragmentManager fragmentManager;

    public Context context;
//    private ACache aCache;

    private BackgroundColorManager colorManager;


    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SelectActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select;
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
//        aCache = ACache.get(context);
        colorManager = new BackgroundColorManager(context);

        //初始化视图
        initView();


    }

    private void initView() {
        //初始化TextView
        InitTextView();

        //初始化ImageView
        InitImageView();

        //初始化Fragment
        InitFragment();

        //初始化ViewPager
        InitViewPager();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {

        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onResume() {
        /**
         * 设置为竖屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        super.onResume();
    }

    /**
     * 初始化头标
     */
    private void InitTextView() {

        //精选头标
        featuredTextView = (TextView) findViewById(R.id.featured_text);
        //游戏头标
        gamesTextView = (TextView) findViewById(R.id.games_text);
        //应用头标
        appsTextView = (TextView) findViewById(R.id.apps_text);

        //设置字体的颜色与大小
        setTextSize();
        resetTextViewTextColor();

        //添加点击事件
        featuredTextView.setOnClickListener(new MyOnClickListener(0));
        gamesTextView.setOnClickListener(new MyOnClickListener(1));
        appsTextView.setOnClickListener(new MyOnClickListener(2));
    }

    /**
     * 初始化页卡内容区
     */
    private void InitViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.vPager);
        mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));

        //让ViewPager缓存2个页面
        mViewPager.setOffscreenPageLimit(2);

        //设置默认打开第一页
        mViewPager.setCurrentItem(0);

        //将顶部文字恢复默认值
        resetTextViewTextColor();
        int viewPagerTextSelectedColor = colorManager.getViewPagerTextSelectedColor();
        featuredTextView.setTextColor(viewPagerTextSelectedColor);
        //设置viewpager页面滑动监听事件
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化动画
     */
    private void InitImageView() {
        //动画图片宽度
        int bmpW;
        cursor = (ImageView) findViewById(R.id.cursor);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // 获取分辨率宽度
        int screenW = dm.widthPixels;

        bmpW = (screenW / 3);

        //设置动画图片宽度
        setBmpW(cursor, bmpW);
        offset = 0;

        //动画图片偏移量赋值
        position_one = (int) (screenW / 3.0);
        position_two = position_one * 2;

    }

    /**
     * 初始化Fragment，并添加到ArrayList中
     */
    private void InitFragment() {
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new FeaturedFragment());
        fragmentArrayList.add(new GamesFragment());
        fragmentArrayList.add(new AppsFragment());

        fragmentManager = getSupportFragmentManager();

    }


    /**
     * 头标点击监听
     */
    private class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        private MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mViewPager.setCurrentItem(index);
        }
    }

    /**
     * 页卡切换监听
     */
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            Animation animation = null;
            switch (position) {

                //当前为页卡1
                case 0:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(position_one, 0, 0, 0);
                        resetTextViewTextColor();
                        featuredTextView.setTextColor(getResources().getColor(R.color.viewpager_selected_color));
                    } else if (currIndex == 2) {//从页卡1跳转转到页卡3
                        animation = new TranslateAnimation(position_two, 0, 0, 0);
                        resetTextViewTextColor();
                        featuredTextView.setTextColor(getResources().getColor(R.color.viewpager_selected_color));
                    }
                    break;

                //当前为页卡2
                case 1:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_one, 0, 0);
                        resetTextViewTextColor();
                        gamesTextView.setTextColor(getResources().getColor(R.color.viewpager_selected_color));
                    } else if (currIndex == 2) { //从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                        resetTextViewTextColor();
                        gamesTextView.setTextColor(getResources().getColor(R.color.viewpager_selected_color));
                    }
                    break;

                //当前为页卡3
                case 2:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_two, 0, 0);
                        resetTextViewTextColor();
                        appsTextView.setTextColor(getResources().getColor(R.color.viewpager_selected_color));
                    } else if (currIndex == 1) {//从页卡1跳转转到页卡2
                        animation = new TranslateAnimation(position_one, position_two, 0, 0);
                        resetTextViewTextColor();
                        appsTextView.setTextColor(getResources().getColor(R.color.viewpager_selected_color));
                    }
                    break;
            }
            currIndex = position;

            animation.setFillAfter(true);// true:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    ;

    /**
     * 设置动画图片宽度
     *
     * @param mWidth
     */
    private void setBmpW(ImageView imageView, int mWidth) {
        ViewGroup.LayoutParams para;
        para = imageView.getLayoutParams();
        para.width = mWidth;
        imageView.setLayoutParams(para);
    }

    /**
     * 将顶部文字恢复默认值
     */
    private void resetTextViewTextColor() {
        int viewPagerTextUnSelectedColor = colorManager.getViewPagerTextUnSelectedColor();
        featuredTextView.setTextColor(viewPagerTextUnSelectedColor);
        gamesTextView.setTextColor(viewPagerTextUnSelectedColor);
        appsTextView.setTextColor(viewPagerTextUnSelectedColor);

    }

    private void setTextSize() {
        int viewPagerTextSize = colorManager.getViewPagerTextSize();
            featuredTextView.setTextSize(viewPagerTextSize);
            gamesTextView.setTextSize(viewPagerTextSize);
            appsTextView.setTextSize(viewPagerTextSize);
    }


}
