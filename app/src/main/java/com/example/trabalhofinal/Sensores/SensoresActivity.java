package com.example.trabalhofinal.Sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhofinal.MenuActivity;
import com.example.trabalhofinal.R;

import java.util.List;

public class SensoresActivity extends MenuActivity implements SensorEventListener {

    private TextView txtRespostaSensores, txtX, txtY, txtZ;

    List<Sensor> listaSensores;

    Sensor acelerometro;
    SensorManager sensorManager;

    Vibrator vibrator;
    boolean turnOnOff = true;
    int count = 0;
    String cameraId;
    CameraManager cameraManager;

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);

        txtRespostaSensores = findViewById(R.id.txtRespostaSensores);
        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        txtZ = findViewById(R.id.txtZ);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        cameraManager = (CameraManager)getSystemService(CAMERA_SERVICE);

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        camera();

    }

    public void lerSensor(View view){
        String result = "";
        for(Sensor cada : listaSensores){
            result += cada.getName() +" | "+ cada.getMaximumRange()+"\n";
        }
        txtRespostaSensores.setText(result+"\n Numero de sensores: "+listaSensores.size());
        if(listaSensores.size() > 20){
            falaSensor("É um Iphone, com tudo que tem direito, tem "+listaSensores.size()+" sensores, até me babei");
        }else if(listaSensores.size() > 10){
            falaSensor("bom... bom... bom..., não é mas tem "+listaSensores.size()+" sensores para brincar");
        }else {
            falaSensor("Ta fraco, esse tijolo tem "+listaSensores.size()+" sensores");
        }

    }

    public void falaSensor (String text){
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.speak(text, 0, Bundle.EMPTY, null);
                } else {
                    System.out.println("erro no speech"+status);
                }
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        txtX.setText(String.valueOf(x));
        txtY.setText(String.valueOf(y));
        txtZ.setText(String.valueOf(z));

        if(x > 7 && y > 7 ){
            count ++;
            if(count > 1){
                flashONOFF(turnOnOff);
                count = 0;
                vibrator.vibrate(500);
            }
        } //da pra melhorar
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Toast.makeText(this, "Recalculando...", Toast.LENGTH_SHORT).show();
    }

    public void camera (){
        try{
            cameraId = cameraManager.getCameraIdList()[0];
        }catch (CameraAccessException e){
            System.out.println(e);
        }
    }

    public void flashONOFF(boolean status){
        try{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                cameraManager.setTorchMode(cameraId, status);
                if(status){
                    turnOnOff = false;
                } else {
                    turnOnOff = true;
                }
            }
        }catch (CameraAccessException e){
            System.out.println(e);
        }
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