package com.example.u1angelgonzalesejercicio2tema4;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Foreground extends AppCompatActivity {

    MediaPlayer mp;
    private IntentFilter intentFilter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        //stopService(new Intent(Foreground.this,
                //ServicioAntivirus.class));

        intentFilter = new IntentFilter("RESPONSE");
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(Foreground.this, ForegroundService.class));
            stopService(new Intent(Foreground.this,
                    ServicioAntivirus.class));
            //finish();
            //startForegroundService(new Intent(Servicio.this, ForegroundService.class));
        } else {
            /*startService(new Intent(Servicio.this,
                    ServicioAntivirus.class));
            finish();*/
            startService(new Intent(Foreground.this,
                    ForegroundService.class));
        }
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(Foreground.this,
                ForegroundService.class));
        super.onDestroy();
    }
}
