package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.trabalhofinal.IMC.MainActivity;
import com.example.trabalhofinal.Sensores.SensoresActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);



    }

    public void openIMC (MenuItem item){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openSensores (MenuItem item){
        Intent intent = new Intent(this, SensoresActivity.class);
        startActivity(intent);
    }
}