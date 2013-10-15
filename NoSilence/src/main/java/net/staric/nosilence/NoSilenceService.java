package net.staric.nosilence;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;


public class NoSilenceService extends Service {
    static final String LOGGING_TAG = "NoSilence";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.v(LOGGING_TAG, "NoSilenceService.onStart()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(LOGGING_TAG, "NoSilenceService.onCreate()");

        registerReceiver(
                new NoSilenceReceiver(),
                new IntentFilter("android.media.RINGER_MODE_CHANGED"));
    }
}

