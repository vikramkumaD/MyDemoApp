package com.example.spidpay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.spidpay.R;

public class OnBaordActivity extends AppCompatActivity {
    Button btn_login;
    TextView tv_signup, tv_logginforgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_baord);
        intliazeView();
    }

    public void intliazeView() {
        tv_logginforgotpassword = findViewById(R.id.tv_logginforgotpassword);
        tv_signup = findViewById(R.id.tv_signup);
        btn_login = findViewById(R.id.btn_login);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_login.setOnClickListener((View v) -> {
            startActivity(new Intent(OnBaordActivity.this, LandingActivity.class));
        });

        tv_signup.setOnClickListener((View v) -> {
            startActivity(new Intent(OnBaordActivity.this, RegisterActivity.class));
        });

        tv_logginforgotpassword.setOnClickListener((View v) -> {
            openForgotPassDialog(v);
        });
    }

    private void openForgotPassDialog(View v) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(OnBaordActivity.this);
        View view = LayoutInflater.from(OnBaordActivity.this).inflate(R.layout.forgotpasswordlayout, null);
        builder.setView(view);
        android.app.AlertDialog forgotdialog = builder.create();
        RelativeLayout relative_cancel = view.findViewById(R.id.relative_cancel);
        relative_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotdialog.dismiss();
            }
        });


        RelativeLayout relative_ok = view.findViewById(R.id.relative_ok);
        relative_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotdialog.dismiss();
            }
        });

        forgotdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        forgotdialog.show();
        forgotdialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(forgotdialog.getWindow().getAttributes());
        int horizontalMargin = (int) getResources().getDimension(R.dimen.marign10dp);
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin = horizontalMargin;
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin = horizontalMargin;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        forgotdialog.getWindow().setAttributes(layoutParams);

    }
}