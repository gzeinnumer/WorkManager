package com.eskalink.workmanager.example1_notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.eskalink.workmanager.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ServiceWorker extends Worker {
    public ServiceWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    private static final String TAG = "NotificationWorker_";

    @NonNull
    @Override
    public Result doWork() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        showNotification("WorkManager kt", currentDateandTime);
        Log.d(TAG, "doWork_: kepanggil1");

        return Result.success();
    }

    private void showNotification(String task, String desc) {
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "task_channel";
        String channelName = "task_name";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new
                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher);
        manager.notify(1, builder.build());
    }
}
