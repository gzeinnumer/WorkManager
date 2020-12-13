package com.eskalink.workmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.eskalink.workmanager.MainActivity;
import com.eskalink.workmanager.example1_notification.ServiceWorker;
import com.eskalink.workmanager.example2_rxjava.ServiceWorker2;
import com.eskalink.workmanager.example3_retrofit.ServiceWorker3;

public class ServiceReceiver extends BroadcastReceiver {
    //akan di aktifkan ketika notifiasi di kirim, janga lupa daftarkan di manifest
    @Override
    public void onReceive(Context context, Intent intent) {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .setRequiresBatteryNotLow(true)
//                .setRequiresCharging(true)
//                .setRequiresStorageNotLow(true)
                .build();

        WorkRequest w1 = new PeriodicWorkRequest.Builder(ServiceWorker.class, MainActivity.INTERVAL, TimeUnit.MINUTES).build();
        WorkManager.getInstance(context).enqueue(w1);

        WorkRequest w2 = new PeriodicWorkRequest.Builder(ServiceWorker2.class, MainActivity.INTERVAL, TimeUnit.MINUTES).build();
        WorkManager.getInstance(context).enqueue(w2);

        WorkRequest w3 = new PeriodicWorkRequest.Builder(ServiceWorker3.class, MainActivity.INTERVAL, TimeUnit.MINUTES).build();
        WorkManager.getInstance(context).enqueue(w3);
    }
}
