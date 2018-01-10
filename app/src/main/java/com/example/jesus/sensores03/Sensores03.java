package com.example.jesus.sensores03;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Sensores03 extends AppCompatActivity implements SensorEventListener{
    int contador;
    double x=0;
    double y=0;
    double z=0;
    TextView tvax;
    TextView tvay;
    TextView tvaz;
    TextView cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores03);

        tvax = (TextView) findViewById(R.id.aceleracionX);
        tvay = (TextView) findViewById(R.id.aceleracionY);
        tvaz = (TextView) findViewById(R.id.aceleracionZ);
        cont = (TextView) findViewById(R.id.contador);

        SensorManager SensorManager = (android.hardware.SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor gyro = SensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SensorManager.registerListener(this,gyro,SensorManager.SENSOR_DELAY_FASTEST);

        new MiAsyncTask().execute();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    class MiAsyncTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            while(true){
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }contador++;
                publishProgress();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            tvax.setText("" + x);
            tvay.setText("" + y);
            tvaz.setText("" + z);
            cont.setText("CONTADOR: " + contador);
        }
    }
}
