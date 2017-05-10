package com.example.sean.adssdk.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.sean.adssdk.R;

/**
 * Created by sean on 2017/5/2.
 */
public class GridTitleHolder extends RecyclerView.ViewHolder{
    public LinearLayout gridTitleLinear;

    public GridTitleHolder(View view){
        super(view);
        gridTitleLinear = (LinearLayout) view.findViewById(R.id.grid_title_linear);
    }
}