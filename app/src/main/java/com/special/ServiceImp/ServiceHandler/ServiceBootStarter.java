package com.special.ServiceImp.ServiceHandler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author Arnar Jonsson
 * @version 0.1
 * @since 5.5.2015
 * Class originally created 9.2.2015
 *
 * ServiceBootStarter
 *
 * Starts AppService.class on phone boot
 */
public class ServiceBootStarter extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startIntent = new Intent(context, AppService.class);
        context.startService(startIntent);
    }
}
