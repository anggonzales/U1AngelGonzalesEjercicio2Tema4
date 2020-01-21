package com.example.u1angelgonzalesejercicio2tema4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

public class Actividad3 extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad3);
        mp = MediaPlayer.create(Actividad3.this, R.raw.musica);
        mp.start();
    }

    public void buscarAntivirus(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=com.avast.android.mobilesecurity&hl=es"));
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        mp.stop();
        super.onStop();
    }
}
