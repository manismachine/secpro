package com.securitypro.proapp.Utility;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import com.securitypro.proapp.Service.appService;


/**
 * Created by Appcrafter on 03-Mar-18.
 */

public class app extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        final Intent intent = new Intent(this, appService.class);
        intent.setAction("camnotification");
        //startService(intent);

        final AudioManager aM = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        if (!aM.isMicrophoneMute()) {
            aM.setMode(AudioManager.MODE_IN_CALL);
            aM.setMicrophoneMute(true);
        }

    }
}
