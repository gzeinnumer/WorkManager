package com.eskalink.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;

import com.eskalink.workmanager.example1_notification.ServiceWorker;
import com.eskalink.workmanager.example2_rxjava.ServiceWorker2;
import com.eskalink.workmanager.example3_retrofit.ServiceWorker3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public static int INTERVAL = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .setRequiresBatteryNotLow(true)
//                .setRequiresCharging(true)
//                .setRequiresStorageNotLow(true)
                .build();

        PeriodicWorkRequest w1 = new PeriodicWorkRequest.Builder(ServiceWorker.class, INTERVAL, TimeUnit.MINUTES).build();
        WorkManager.getInstance(getApplicationContext()).enqueue(w1);

        WorkRequest w2 = new PeriodicWorkRequest.Builder(ServiceWorker2.class, INTERVAL, TimeUnit.MINUTES).build();
        WorkManager.getInstance(getApplicationContext()).enqueue(w2);

        WorkRequest w3 = new PeriodicWorkRequest.Builder(ServiceWorker3.class, INTERVAL, TimeUnit.MINUTES).build();
        WorkManager.getInstance(getApplicationContext()).enqueue(w3);
    }
}
