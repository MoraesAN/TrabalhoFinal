package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.trabalhofinal.IMC.MainActivity;
import com.example.trabalhofinal.Sensores.SensoresActivity;

public class HomeActivity extends AppCompatActivity {

    private MediaPlayer musica ;
    private ImageView som;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        som = findViewById(R.id.som);

        musica = MediaPlayer.create(HomeActivity.this, R.raw.mario);

        musica.start();
        som.setBackgroundResource(R.drawable.som_ligado);
    }

    public void ligaDesliga(View V){
        if(musica.isPlaying()){
            som.setBackgroundResource(R.drawable.som_desligado);
            musica.pause();
        } else {
            som.setBackgroundResource(R.drawable.som_ligado);
            musica.start();
        }
    }

    public void homeIMC (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void homeSensor (View view){
        Intent intent = new Intent(this, SensoresActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        if(musica.isPlaying()) {
            musica.pause();
        }
        super.onPause();
    }
}