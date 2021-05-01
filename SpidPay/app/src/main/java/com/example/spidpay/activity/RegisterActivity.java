package com.example.spidpay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spidpay.R;

public class RegisterActivity extends AppCompatActivity {
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        intiliazeView();
    }

    public void intiliazeView() {
        btn_submit=findViewById(R.id.btn_submit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_submit.setOnClickListener((View v) -> {
            startActivity(new Intent(RegisterActivity.this, LandingActivity.class));
            finish();
        });
    }
}