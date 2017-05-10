package com.example.sean.adssdk.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.example.sean.adssdk.R;

/**
 * Created by sean on 2017/5/3.
 */

public class TwoButtonViewHolder extends RecyclerView.ViewHolder {
    public FrameLayout categoryFrame;
    public FrameLayout topFrame;

    public TwoButtonViewHolder(View view){
        super(view);

        categoryFrame = (FrameLayout) view.findViewById(R.id.two_button_category);
        topFrame = (FrameLayout) view.findViewById(R.id.two_button_list);
    }
}
