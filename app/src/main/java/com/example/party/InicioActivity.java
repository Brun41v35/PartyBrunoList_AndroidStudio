package com.example.party;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.party.Adaptador.ListarPessoaActivity;

public class InicioActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        lottieAnimationView = (LottieAnimationView) findViewById(R.id.idPresent);
        startu();
    }

    public void outraTela(View view) {
        Intent intent = new Intent(this, ListarPessoaActivity.class);
        startActivity(intent);
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
