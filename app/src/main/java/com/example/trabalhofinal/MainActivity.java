package com.example.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edtPeso, edtAltura;
    private TextView txtResultadoIMC;
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


    }

    public void calcular(View view){
        double result = Imc.calcularIMC(Double.parseDouble(edtPeso.getText().toString()), Double.parseDouble(edtAltura.getText().toString()));

        if (result >= 18.9 && result <= 24.9){
            txtResultadoIMC.setText("NORMAL - "+ String.format("%.1f", result));
            imgIMC.setVisibility(View.VISIBLE);
            imgIMC.setBackgroundResource(R.drawable.imcnormal);
            fala("você ta fitness, maravilhoso!!!");

        }else if (result >= 25.0 && result <= 29.9){
            txtResultadoIMC.setText("SOBREPESO - "+ String.format("%.1f", result));
            imgIMC.setVisibility(View.VISIBLE);
            imgIMC.setBackgroundResource(R.drawable.imcacima);
            fala("você ta fofo, com uns gordinhos para apertar!!!");

        }else if (result >= 30.0 && result <= 39.9){
            txtResultadoIMC.setText("OBESIDADE - "+ String.format("%.1f", result));
            imgIMC.setVisibility(View.VISIBLE);
            imgIMC.setBackgroundResource(R.drawable.imcobesidade);
            fala("você está gordo, abre o olho!!!");

        }else if (result >= 40.0){
            txtResultadoIMC.setText("OBESIDADE GRAVE - "+ String.format("%.1f", result));
            imgIMC.setVisibility(View.VISIBLE);
            imgIMC.setBackgroundResource(R.drawable.imcobesidade3);
            fala("você ta beeeeem gordo, lustroso!!!");

        }else if (result <= 18.8){
            txtResultadoIMC.setText("MAGREZA - "+ String.format("%.1f", result));
            imgIMC.setVisibility(View.VISIBLE);
            imgIMC.setBackgroundResource(R.drawable.imcabaixo);
            fala("você ta bem magrinho, né!!!");

        }

    }
    public void fala (String text){
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }



}