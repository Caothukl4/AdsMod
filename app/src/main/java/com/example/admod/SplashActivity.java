package com.example.admod;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admod.databinding.ActivitySplashBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Khởi tạo View Binding
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Khởi tạo AdMob
        MobileAds.initialize(this, initializationStatus -> {});
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);

        // Chờ 5 giây rồi chuyển sang màn hình Onboarding
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, OnboardingActivity.class));
            finish();
        }, 5000);
    }
}

