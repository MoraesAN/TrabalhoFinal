package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
/*
*
*                      @@@@@@@@@@@@@@@@ Nathalia Moraes @@@@@@@@@@@@@@@@@@@
*
* */
public class MainActivity extends AppCompatActivity {

    private EditText edtPeso, edtAltura;

    private TextView txtResultadoIMC, txtPesoIdeal;
    private ImageView imgIMC;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAltura = findViewById(R.id.edtAltura);
        edtPeso = findViewById(R.id.edtPeso);
        txtResultadoIMC = findViewById(R.id.txtResultadoIMC);
        imgIMC = findViewById(R.id.imgIMC);
        txtPesoIdeal = findViewById(R.id.txtPesoIdeal);

        edtAltura.addTextChangedListener(new MaskWatcher("#.##"));

    }

    public void calcular(View view){
        double result = Imc.calcularIMC(Double.parseDouble(edtPeso.getText().toString()), Double.parseDouble(edtAltura.getText().toString()));

        if (result >= 18.9 && result <= 24.9){
            txtResultadoIMC.setText(getString(R.string.imcNormal)+ String.format("%.1f", result)+"Kg/m²");
            imgIMC.setVisibility(View.VISIBLE);
            imgIMC.setBackgroundResource(R.drawable.imcnormal);
        }else if (result >= 25.0 && result <= 29.9){
            txtResultadoIMC.setText(getString(R.string.imcSobrepeso)+ String.format("%.1f", result)+"Kg/m²");
            imgIMC.setVisibility(View.VISIBLE);
            imgIMC.setBackgroundResource(R.drawable.imcacima);
        }else if (result >= 30.0 && result <= 39.9){
            txtResultadoIMC.setText(getString(R.string.imcObeso)+ String.format("%.1f", result)+"Kg/m²");
            imgIMC.setVisibility(View.VISIBLE);
            imgIMC.setBackgroundResource(R.drawable.imcobesidade);
        }else if (result >= 40.0){
            txtResultadoIMC.setText(getString(R.string.imcObesoGrave)+ String.format("%.1f", result)+"Kg/m²");
            imgIMC.setVisibility(View.VISIBLE);
            imgIMC.setBackgroundResource(R.drawable.imcobesidade3);
        }else if (result <= 18.8){
            txtResultadoIMC.setText(getString(R.string.imcMagreza)+ String.format("%.1f", result)+"Kg/m²");
            imgIMC.setVisibility(View.VISIBLE);
            imgIMC.setBackgroundResource(R.drawable.imcabaixo);
        }
        txtPesoIdeal.setText(getString(R.string.pesoIdeal)+String.format("%.1f", Imc.pesoIdeal(Double.parseDouble(edtAltura.getText().toString())))+"Kg/m²");
        fala(getString(R.string.nota));
    }
    public void fala (String text){
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.speak(text, TextToSpeech.QUEUE_ADD, null);
                }
            }
        });
    }



}