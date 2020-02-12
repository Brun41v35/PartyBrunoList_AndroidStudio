package com.example.party.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.party.InicioActivity;
import com.example.party.R;

public class SplashScreenActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottieAnimationView);
        startu();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intencao = new Intent(getBaseContext(), InicioActivity.class);
                startActivity(intencao);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

    private void startu() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lottieAnimationView.setProgress((Float) valueAnimator.getAnimatedValue());
            }
        });
        if (lottieAnimationView.getProgress() == 0f) {
            animator.start();
        } else {
            lottieAnimationView.setProgress(0f);
        }
    }
}
