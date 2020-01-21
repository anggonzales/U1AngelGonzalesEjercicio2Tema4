package com.example.u1angelgonzalesejercicio2tema4;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.net.URLEncoder;

public class ServicioAntivirus extends Service {

    private static final int ID_NOTIFICACION_CREAR = 1;
    public static final String NOTIFICATION_CHANNEL_ID = "1000";
    public static final String NOTIFICATION_CHANNEL_NAME = "UNJBG";

    @Override
    public void onCreate() {
        Toast.makeText(this, "Servicio creado",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intenc, int flags, int idArranque) {

        NotificationCompat.Builder notific = new NotificationCompat.Builder(this)
                .setContentTitle("Antivirus")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Presione en iniciar para realizar búsqueda de virus");

        //Para lanzar una actividad
        PendingIntent intencionPendiente = PendingIntent.getActivity(
                this, 0, new Intent(this, Foreground.class), 0);
        notific.setContentIntent(intencionPendiente)
                .addAction(android.R.drawable.ic_menu_call, "INICIAR", intencionPendiente);

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(
                            NOTIFICATION_CHANNEL_ID,
                            NOTIFICATION_CHANNEL_NAME,
                            NotificationManager.IMPORTANCE_LOW);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(R.color.colorAccent);
            notificationManager.createNotificationChannel(notificationChannel);
            notific.setChannelId(NOTIFICATION_CHANNEL_ID);
        }
        //notificationManager.notify(ID_NOTIFICACION_CREAR, notific.build());
        //notificationManager.notify(2, notific.build());
        //notificationManager.notify(3, notific.build());
        Toast.makeText(this, "Servicio arrancado " + idArranque,
                Toast.LENGTH_SHORT).show();
        startForeground(101, notific.build());
        return START_STICKY;

    }

    @Override
    public void onDestroy() {


        Toast.makeText(this, "Servicio detenido",
                Toast.LENGTH_SHORT).show();

        //Eliminando la notificacion
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(ID_NOTIFICACION_CREAR);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(ServicioAntivirus.this, ForegroundService.class));
            //startService(new Intent(ServicioAntivirus.this,Foreground.class));

        } else {


        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
