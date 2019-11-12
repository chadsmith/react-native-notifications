package com.wix.reactnativenotifications.gcm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;

public class FcmInstanceIdRefreshHandlerJob extends JobIntentService {

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, FcmInstanceIdRefreshHandlerJob.class, 1, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        IFcmToken gcmToken = FcmToken.get(this);
        if (gcmToken == null) {
            return;
        }

        if (intent.getBooleanExtra(FcmInstanceIdRefreshHandlerService.EXTRA_IS_APP_INIT, false)) {
            gcmToken.onAppReady();
        } else if (intent.getBooleanExtra(FcmInstanceIdRefreshHandlerService.EXTRA_MANUAL_REFRESH, false)) {
            gcmToken.onManualRefresh();
        } else {
            gcmToken.onNewTokenReady();
        }
    }
}
