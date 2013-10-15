package net.staric.nosilence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;


public class NoSilenceReceiver extends BroadcastReceiver {
    static final String LOGGING_TAG = "NoSilence";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) return;

        if (action.compareTo(Intent.ACTION_BOOT_COMPLETED) == 0) {
            context.startService(new Intent(context, NoSilenceService.class));
        } else if (action.compareTo("android.media.RINGER_MODE_CHANGED") == 0) {
            final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if (intent.getIntExtra(AudioManager.EXTRA_RINGER_MODE, 1) == 0) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
                            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                        }
                    }
                }, 1000);
            }
        }
    }
}