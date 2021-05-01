package com.example.mydemo.Util;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.mydemo.R;

public class Constant {
    public static String BASR_URL = "https://staging.pearpartner.com/";


    public static ProgressDialog setupLoading(Context context) {
        ProgressDialog progress = new ProgressDialog(context, R.style.MyTheme);
        progress.setCancelable(false);
        progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progress.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.progress_bar_states));
        return progress;
    }
}
