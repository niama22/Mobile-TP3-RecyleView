package com.example.startlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class splashActivity extends AppCompatActivity {
    private ImageView chamchon, mester_salta3, pasit, sundy, spongbob,spongbob2,chafia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        chamchon = findViewById(R.id.chamchon);
        mester_salta3 = findViewById(R.id.mester_salta3);
        pasit = findViewById(R.id.pasit);
        sundy = findViewById(R.id.sundy);
        spongbob = findViewById(R.id.spongbob);

        chafia=findViewById(R.id.chfia);
        animateImages();
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    Intent intent  = new Intent(splashActivity.this, ListActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();


    }

    private void animateImages() {
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.drawable.animation);



        spongbob.postDelayed(() -> {
            chamchon.setVisibility(View.VISIBLE);
            chamchon.startAnimation(fadeIn);
        }, 500);

        chamchon.postDelayed(() -> {
            pasit.setVisibility(View.VISIBLE);
            pasit.startAnimation(fadeIn);
        }, 1000);

        pasit.postDelayed(() -> {
            sundy.setVisibility(View.VISIBLE);
            sundy.startAnimation(fadeIn);
        }, 1500);

        sundy.postDelayed(() -> {
            mester_salta3.setVisibility(View.VISIBLE);
            mester_salta3.startAnimation(fadeIn);
        }, 2000);

        mester_salta3.postDelayed(() -> {
            chafia.setVisibility(View.VISIBLE);
            chafia.startAnimation(fadeIn);

            chafia.postDelayed(() -> {
                spongbob.setVisibility(View.VISIBLE);
                spongbob.startAnimation(fadeIn);
                spongbob.animate().rotation(360f).setDuration(2000)
                        .withEndAction(() -> spongbob.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000)
                                .withEndAction(() -> spongbob.animate().translationYBy(1000f).setDuration(2000)
                                        .withEndAction(() -> spongbob.animate().alpha(0f).setDuration(6000))));
            }, 500);

        }, 2500);

    }
    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }

}
