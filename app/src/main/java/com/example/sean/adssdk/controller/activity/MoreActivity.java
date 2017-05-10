package com.example.sean.adssdk.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sean.adssdk.R;

public class MoreActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MoreActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_activty);
    }
}
