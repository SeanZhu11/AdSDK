package com.example.sean.adssdk.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.sean.adssdk.R;

/**
 * Created by sean on 2017/5/3.
 */

public class MorePicHolder extends RecyclerView.ViewHolder {
    public ImageView morePicIV;

    public MorePicHolder(View view){
        super(view);
        morePicIV = (ImageView) view.findViewById(R.id.more_pic_iv);
    }
}
