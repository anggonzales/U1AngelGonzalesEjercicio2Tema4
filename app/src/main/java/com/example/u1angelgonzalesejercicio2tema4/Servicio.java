package com.example.u1angelgonzalesejercicio2tema4;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Servicio extends AppCompatActivity {

    private IntentFilter intentFilter;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        setContentView(R.layout.activity_main);

        intentFilter = new IntentFilter("RESPONSE");
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(Servicio.this, ServicioAntivirus.class));
            finish();
            //startForegroundService(new Intent(Servicio.this, ForegroundService.class));
        } else {
            /*startService(new Intent(Servicio.this,
                    ServicioAntivirus.class));
            finish();*/
            /*startService(new Intent(Servicio.this,
                    ServicioAntivirus.class));*/
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
