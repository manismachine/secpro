package com.securitypro.proapp.Receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;

import com.securitypro.proapp.Activity.Splash;
import com.securitypro.proapp.Service.appService;


public class BootReceiver extends BroadcastReceiver {
    private final String TAG = "BOOTREC";

    @Override
    public void onReceive(Context context, Intent intent) {

//        Log.trace(RecorderConstants.DEBUG_TAG,"Intent Action : "+intent.getAction());
        //----------------------------------------------------------------------
        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT) ||
                intent.getAction().equals(Intent.ACTION_SCREEN_OFF) ||
                intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED) ||
                intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION) ||
                intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION) ||
                intent.getAction().equals(Intent.ACTION_MEDIA_BUTTON) ||
                intent.getAction().equals(Intent.ACTION_BATTERY_OKAY) ||
                intent.getAction().equals(Intent.ACTION_POWER_CONNECTED) ||
                intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED) ||
                intent.getAction().equals(Intent.ACTION_SCREEN_ON) ||
                intent.getAction().equals(Intent.ACTION_DATE_CHANGED)) {

            Intent in = new Intent(context, appService.class);
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(in);
        }

        //------------------------------------------------------------------------------------

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) ||
                intent.getAction().equals(Intent.ACTION_REBOOT) ||
                intent.getAction().equals(Intent.ACTION_USER_INITIALIZE)) {

            PackageManager pm = context.getPackageManager();
            ComponentName componentName = new ComponentName(context, Splash.class);
            pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

            Intent intent2 = new Intent(context, Splash.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);

        }

        //-------------------------------------------------------------------------------------

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Intent intent1 = new Intent(context, appService.class);
            context.startService(intent1);
        }
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Intent intent1 = new Intent(context, appService.class);
            context.startService(intent1);
        }

        if (intent.getAction().equals("Again")) {
            Intent intent1 = new Intent(context, appService.class);
            context.startService(intent1);
        }

        //--------------------------------------------------------------------------------------
    }
}
