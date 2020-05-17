package com.eskalink.workmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.concurrent.TimeUnit;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class NotificationReceiver extends BroadcastReceiver {
    //akan di aktifkan ketika notifiasi di kirim, janga lupa daftarkan di manifest
    @Override
    public void onReceive(Context context, Intent intent) {
        WorkManager mWorkManager = WorkManager.getInstance();
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(NotificationWorker.class, 15, TimeUnit.MINUTES).build();
        mWorkManager.enqueue(workRequest);
    }
}
