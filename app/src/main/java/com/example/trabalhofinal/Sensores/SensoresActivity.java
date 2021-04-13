package com.example.trabalhofinal.Sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhofinal.R;

import java.util.List;

public class SensoresActivity extends AppCompatActivity implements SensorEventListener {

    private TextView txtRespostaSensores;

    List<Sensor> listaSensores;

    Sensor acelerometro;
    SensorManager sensorManager;

    Vibrator vibrator;
    CameraManager cameraManager;

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);

        txtRespostaSensores = findViewById(R.id.btnSensores);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        cameraManager = (CameraManager)getSystemService(CAMERA_SERVICE);

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);
    }

    public void lerSensor(View view){
        String result = "";
        for(Sensor cada : listaSensores){
            result += cada.getName() +" | "+ cada.getMaximumRange()+"\n";
        }
        txtRespostaSensores.setText(result+"\n Numero de sensore: "+listaSensores.size());

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        if(x > 30 && y > 30 && z > 30){

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Toast.makeText(this, "Recalculando...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, acelerometro, sensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this, acelerometro);
        super.onPause();
    }
}