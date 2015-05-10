package com.special.ServiceImp.ServiceHandler;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.special.ServiceImp.Interfaces.AppInterface;

/**
 * @author Arnar Jonsson
 * @version 0.1
 * @since ..
 * Originally created on ..
 */
public class AppServiceSignal {

    ///
    // AppServiceSignal Variables
    //
    ///

    private AppInterface callBack;
    private Context context;
    private boolean isBound;

    ///
    // AppServiceSignal Constructors
    //
    ///

    public AppServiceSignal(Context context) {

        ServiceConnection connection = createServiceConnection(context);

        try {
            context.bindService(new Intent(context, AppService.class), connection, context.BIND_ABOVE_CLIENT);
            isBound = true;
        }
        catch (Exception e) {
            isBound = false;
        }

    }

    ///
    // AppServiceSignal Public Methods
    //
    ///

    public boolean isBound() {
        return isBound;
    }

    public AppInterface getCallBack() {
        return callBack;
    }

    ///
    // AppServiceSignal Connection object
    //
    ///

    private ServiceConnection createServiceConnection(Context context) {
        return new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                callBack = ((AppService.AppServiceBinder)iBinder).getCallBack();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                callBack = null;
            }
        };
    }


}
