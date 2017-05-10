package com.example.sean.adssdk.model.info;

/**
 * Created by Sean on 17/4/20.
 */

public class AdsInfo {
    private final float FullMark = 5.0f;

    private String adsPicUrl; //图片地址
//    private String sponsored; //赞助商
    private String adsDescription; //广告介绍
    private String adsUrl;//广告下载地址
    private String adsBundleId;//广告包名
    private String adsTitle;//广告名称
    private String adsIcon;//广告icon
    private float rating;//广告评价
    private String adsDownLoadCount;//广告下载次数
    private String category;//类别
    private String appSize;//包大小
    private String actionText;//下载是否免费
    private int appShowType; //展现的形式

    public AdsInfo(){

    }

    public void setAdsPicUrl(String adsPicUrl){
        this.adsPicUrl = adsPicUrl;
    }
    public String getAdsPicUrl(){
        if(adsPicUrl == null){
            adsPicUrl = "";
        }
        return adsPicUrl;
    }

    public void setAdsDescription(String adsDescription){
        this.adsDescription = adsDescription;
    }
    public String getAdsDescription(){
        if(adsDescription == null){
            adsDescription = "";
        }
        return adsDescription;
    }

    public void setAdsUrl(String adsUrl){
        this.adsUrl = adsUrl;
    }
    public String getAdsUrl(){
        if(adsUrl == null){
            adsUrl = "";
        }
        return adsUrl;
    }

    public void setAdsBundleId(String adsBundleId){
        this.adsBundleId = adsBundleId;
    }
    public String getAdsBundleId(){
        if(adsBundleId == null){
            adsBundleId = "";
        }
        return adsBundleId;
    }

    public void setAdsTitle(String adsTitle){
        this.adsTitle = adsTitle;
    }
    public String getAdsTitle(){
        if(adsTitle == null){
            adsTitle = "";
        }
        return adsTitle;
    }

    public void setRating(float rating){
        if(rating >= FullMark){
            rating = FullMark;
        }
        this.rating = rating;
    }
    public float getRating(){
        return rating;
    }

    public void setAdsDownLoadCount(String adsDownLoadCount){
        this.adsDownLoadCount = adsDownLoadCount;
    }
    public String getAdsDownLoadCount(){
        if(adsDownLoadCount == null){
            adsDownLoadCount = "";
        }
        return adsDownLoadCount;
    }

    public void setCategory(String category){
        this.category = category;
    }
    public String getCategory(){
        if(category == null){
            category = "";
        }
        return category;
    }

    public void setAppSize(String appSize){
        this.appSize = appSize;
    }
    public String getAppSize(){
        if(appSize == null){
            appSize = "";
        }
        return appSize;
    }

    public void setActionText(String actionText){
        this.actionText = actionText;
    }
    public String getActionText(){
        if(actionText == null){
            actionText = "";
        }
        return actionText;
    }

    public void setAdsIcon(String adsIcon){
        this.adsIcon = adsIcon;
    }
    public String getAdsIcon(){
        if(adsIcon == null){
            adsIcon = "";
        }
        return adsIcon;
    }

    public void setAppShowType(int appShowType){
        this.appShowType = appShowType;
    }
    public int getAppShowType(){
        return appShowType;
    }
}
