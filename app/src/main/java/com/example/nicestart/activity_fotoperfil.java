package com.example.nicestart;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class activity_fotoperfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activa modo pantalla completa/control de barras del sistema
        EdgeToEdge.enable(this);

        // Carga el layout activity_fotoperfil.xml
        setContentView(R.layout.activity_fotoperfil);

        // Ajusta el padding del layout para no tapar contenido con la barra de estado/nav
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencia al ImageView donde irá la imagen redonda
        ImageView fotoperfil = findViewById(R.id.imagenRedonda);

        // Glide: carga eficiente de imágenes con animación y recorte circular
        Glide.with(this)
                .load(getDrawable(R.drawable.son)) // Imagen que se va a cargar
                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.TransParente))) // Color temporal mientras carga
                .transition(DrawableTransitionOptions.withCrossFade(100)) // Animación de desvanecimiento
                .centerCrop() // Escala la imagen sin deformarla
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Guarda imagen en caché para futuras cargas
                .circleCrop() // Recorte circular (foto de perfil)
                .into(fotoperfil); // Inserta imagen en el ImageView
    }
}
