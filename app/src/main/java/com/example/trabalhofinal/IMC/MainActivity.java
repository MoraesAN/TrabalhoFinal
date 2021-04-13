package com.example.trabalhofinal.IMC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalhofinal.R;

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
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAltura = findViewById(R.id.edtAltura);
        edtPeso = findViewById(R.id.edtPeso);
        txtResultadoIMC = findViewById(R.id.txtResultadoIMC);
        imgIMC = findViewById(R.id.imgIMC);
        txtPesoIdeal = findViewById(R.id.txtPesoIdeal);
        btnCalcular = findViewById(R.id.btnCalcular);

        edtAltura.addTextChangedListener(new MaskWatcher("#.##"));

        edtAltura.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    btnCalcular.performClick();
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(edtAltura.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });


    }

    public void calcular(View view){

        if(edtPeso.getText().toString().isEmpty() || edtAltura.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(this, "Preencha os campos para calcular o IMC", Toast.LENGTH_LONG);
            TextView v = toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.MAGENTA);
            toast.show();
        } else {
            double result = Imc.calcularIMC(Double.parseDouble(edtPeso.getText().toString()), Double.parseDouble(edtAltura.getText().toString()));

            if (result >= 18.9 && result <= 24.9) {
                txtResultadoIMC.setText(getString(R.string.imcNormal) + " " + String.format("%.1f", result) + "Kg/m²");
                imgIMC.setBackgroundResource(R.drawable.imcnormal);
                imgIMC.setVisibility(View.VISIBLE);
            } else if (result >= 25.0 && result <= 29.9) {
                txtResultadoIMC.setText(getString(R.string.imcSobrepeso) + " " + String.format("%.1f", result) + "Kg/m²");
                imgIMC.setVisibility(View.VISIBLE);
                imgIMC.setBackgroundResource(R.drawable.imcacima);
            } else if (result >= 30.0 && result <= 39.9) {
                txtResultadoIMC.setText(getString(R.string.imcObeso) + " " + String.format("%.1f", result) + "Kg/m²");
                imgIMC.setVisibility(View.VISIBLE);
                imgIMC.setBackgroundResource(R.drawable.imcobesidade);
            } else if (result >= 40.0) {
                txtResultadoIMC.setText(getString(R.string.imcObesoGrave) + " " + String.format("%.1f", result) + "Kg/m²");
                imgIMC.setVisibility(View.VISIBLE);
                imgIMC.setBackgroundResource(R.drawable.imcobesidade3);
            } else if (result <= 18.8) {
                txtResultadoIMC.setText(getString(R.string.imcMagreza) + " " + String.format("%.1f", result) + "Kg/m²");
                imgIMC.setVisibility(View.VISIBLE);
                imgIMC.setBackgroundResource(R.drawable.imcabaixo);
            }
            //txtPesoIdeal.setText(getString(R.string.pesoIdeal) + " " + String.format("%.1f", Imc.pesoIdeal(Double.parseDouble(edtAltura.getText().toString()))) + "Kg/m²");
            fala(getString(R.string.nota));

            btnCalcular.setOnClickListener(v -> limpaBtn(v));
            btnCalcular.setText("Limpar");
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

    public void limpaBtn(View view){
        edtAltura.setText("");
        edtPeso.setText("");
        txtPesoIdeal.setText("");
        txtResultadoIMC.setText("");
        imgIMC.setBackground(null);
        imgIMC.setVisibility(View.INVISIBLE);
        tts.shutdown();

        btnCalcular.setOnClickListener(v -> calcular(v));
        btnCalcular.setText("Calcular");

    }

    @Override
    protected void onPause() {
        if (tts.isSpeaking()) tts.shutdown();
        super.onPause();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

}