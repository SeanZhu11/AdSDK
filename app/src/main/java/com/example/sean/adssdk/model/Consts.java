package com.example.sean.adssdk.model;

/**
 * Created by Sean on 17/4/20.
 */

public class Consts {
    //服务器应返回的数据
    public static final String AdsPictureUrl = "picture";
    public static final String AdsIcon = "icon";
    public static final String AdsDescription = "description";
    public static final String AdsUrl = "url";
    public static final String AdsBundleId = "bundleId";
    public static final String AdsTitle = "title";
    public static final String AdsRating = "rating";
    public static final String AdsDownLoadCount = "downloadCount";
    public static final String Category = "category";
    public static final String AdsSize = "adsSize";
    public static final String ActionText = "actionText";


    //向服务器提交的数据
    public static final String CountryCode = "countryCode";
    public static final String DeviceID = "deviceID";
    public static final String UserID = "userID";
    public static final String AppID = "appID";

    //网络请求的BaseUrl
    //TODO 补全此处的URL
    public static final String RequestBaseURL = "";



    //从服务器返回的Json串中的字段
    public static final String Data = "data";
    public static final String Game = "game";
    public static final String Application = "application";

    //大幅广告墙的样式
    public static final int WallType = 0;
    //表格布局的样式
    public static final int GridType = 1;
    //表格布局的Title
    public static final int GridTitle = 11;
    //游戏应用分类中的显示
    public static final int CategoryButtonType = 123;


    //MoreActivity中显示的布局
    public static final int MoreType = 23;
    //MoreActivity中的大图
    public static final int MorePic = 666;


    //完成网络请求后的message
    public static final int Success = 1321;
    public static final int Fail = 1322;

    //自定义初始化的颜色值
    public static final String ToolbarBackgroundColor = "ToolbarBackgroundColor";
    public static final String ToolbarHeight = "ToolbarHeight";

    public static final String ToolbarTitleColor = "ToolbarTitleColor";
    public static final String ToolbarTitleSize = "ToolbarTitleSize";
    public static final String ToolbarTitle = "ToolbarTitle";


    public static final String ViewPagerTextSize = "ViewPagerTextSize";
    public static final String ViewPagerTextSelectedColor = "ViewPagerTextSelectedColor";
    public static final String ViewPagerTextUnSelectedColor = "ViewPagerTextUnSelectedColor";

}
