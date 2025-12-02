package com.example.nicestart;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Muestra el layout splash.xml
        setContentView(R.layout.activity_splash);

        // Lanza el temporizador que abrirá Login después de 5 segundos
        openApp();

        // Imagen de fondo del splash animada con Glide
        ImageView mSea = findViewById(R.id.Backview);

        Glide.with(this)
                .load(getDrawable(R.drawable.marchuli))
                .transition(DrawableTransitionOptions.withCrossFade(100))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.logofuego_background)))
                .into(mSea);

        // Animación glow del logo
        ImageView fuego = findViewById((R.id.logo));
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.glow);
        fuego.startAnimation(myanim);
    }

    // Espera 5 segundos y abre Login
    private void openApp() {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }, 5000); // 5000 ms = 5s
    }
}
