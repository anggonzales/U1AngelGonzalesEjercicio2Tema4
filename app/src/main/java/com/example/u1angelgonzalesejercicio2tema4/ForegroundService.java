package com.example.u1angelgonzalesejercicio2tema4;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ForegroundService extends Service {

    private static final int ID_NOTIFICACION_CREAR = 2;
    public static final String NOTIFICATION_CHANNEL_ID = "2000";
    public static final String NOTIFICATION_CHANNEL_NAME = "UPT";

    @Override
    public void onCreate() {
        Toast.makeText(this, "Servicio creado",
                Toast.LENGTH_SHORT).show();

        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent();
                        i.setClass(getApplicationContext(), Actividad3.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        stopService(new Intent(ForegroundService.this,
                                ForegroundService.class));
                        //startActivity(new Intent(ForegroundService.this, Actividad3.class));
                        /*mp = MediaPlayer.create(Actividad3.this, R.raw.musica);
                        mp.start();*/
                    }
                }, 8000);
    }

    @Override
    public int onStartCommand(Intent intenc, int flags, int idArranque) {

        NotificationCompat.Builder notific = new NotificationCompat.Builder(this)
                .setContentTitle("Antivirus")
                .setSmallIcon(R.mipmap.ic_stat_warning)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        android.R.drawable.ic_dialog_alert))
                .setContentText("Buscando virus")
                .setContentText(Html.fromHtml("<b>Analizando</b> <u>búsqueda<i> exahustiva</i></u>"))
                .setDefaults(Notification.DEFAULT_VIBRATE);

        /*PendingIntent intencionPendiente = PendingIntent.getActivity(
                this, 1, new Intent(this, Actividad3.class), 0);

        notific.setContentIntent(intencionPendiente)
                .addAction(android.R.drawable.ic_menu_call, "INICIAR", intencionPendiente)*/;

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
                Toast.LENGTH_LONG).show();
        startForeground(101, notific.build());
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Servicio detenido Foregrund",
                Toast.LENGTH_SHORT).show();

        //Eliminando la notificacion
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(ID_NOTIFICACION_CREAR);

        /*stopService(new Intent(ForegroundService.this,
                ForegroundService.class));*/

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(ForegroundService.this, Actividad3.class));

        } else {

        }*/


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
