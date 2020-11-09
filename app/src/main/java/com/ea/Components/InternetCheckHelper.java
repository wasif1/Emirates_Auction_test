package com.ea.Components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class InternetCheckHelper {
    private static final String TAG = InternetCheckHelper.class.getSimpleName();
    // CALL BACK
    private InternetCheckListener internetCheckListener;
    private Context mContext;


    /**
     * ON CONNECTIVITY CHANGE
     */
    private BroadcastReceiver connectivityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = null;
            if (cm != null) {
                activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null
                        && activeNetwork.isConnectedOrConnecting();

                // send call back
                if (internetCheckListener != null) {
                    internetCheckListener.onNetworkConnectionChanged(isConnected);
                }
            }
        }
    };


    /**
     * CONSTRUCTOR
     */
    public InternetCheckHelper(Context context, InternetCheckListener internetCheckListener) {
        this.internetCheckListener = internetCheckListener;
        this.mContext = context;
        try {
            context.registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * CHECK IF USER IS CONNECTED TO INTERNET
     * NON STATIC METHOD FOR REAL TIME CHECK AND CALLBACK
     */
    public static boolean isConnected() {
        boolean internetCheck = false;
        InetAddress inetAddress = null;

        try {
            Future<InetAddress> future = Executors.newSingleThreadExecutor().submit(new Callable<InetAddress>() {
                @Override
                public InetAddress call() throws Exception {
                    try {
                        return InetAddress.getByName("google.com");
                    } catch (UnknownHostException e) {
                        return null;
                    }
                }
            });
            inetAddress = future.get(1000, TimeUnit.MILLISECONDS);
            future.cancel(true);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            if (!TextUtils.isEmpty(e.getMessage())) {
                Log.e(TAG, e.getMessage());
            }
        }

        // get internet status
        internetCheck = inetAddress != null && !TextUtils.isEmpty(inetAddress.toString());

        return internetCheck;
    }


    /**
     * STOP INTERNET CHECK BROADCAST RECEIVER
     */
    public void stopInternetCheck() {
        try {
            if (mContext != null && connectivityReceiver != null) {
                mContext.unregisterReceiver(connectivityReceiver);
            }
        } catch (IllegalArgumentException e) {

            e.printStackTrace();
        }

    }

    /**
     * CHECK IF USER IS CONNECTED TO INTERNET
     * NON STATIC METHOD FOR REAL TIME CHECK AND CALLBACK
     */
    public boolean isConnectedWithCallback() {
        boolean internetCheck = false;
        InetAddress inetAddress = null;

        try {
            Future<InetAddress> future = Executors.newSingleThreadExecutor().submit(new Callable<InetAddress>() {
                @Override
                public InetAddress call() throws Exception {
                    try {
                        return InetAddress.getByName("google.com");
                    } catch (UnknownHostException e) {
                        return null;
                    }
                }
            });
            inetAddress = future.get(1000, TimeUnit.MILLISECONDS);
            future.cancel(true);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            if (!TextUtils.isEmpty(e.getMessage())) {
                Log.e(TAG, e.getMessage());
            }
        }

        // get internet status
        internetCheck = inetAddress != null && !TextUtils.isEmpty(inetAddress.toString());

        // send callback
        if (internetCheckListener != null) {
            internetCheckListener.onNetworkConnectionChanged(internetCheck);
        }

        return internetCheck;
    }


    /**
     * Callback used to notify about connectivity status
     */
    public interface InternetCheckListener {
        /**
         * ON NETWORK CHANGE CALL BACK
         *
         * @param isConnected state of the connection
         */
        void onNetworkConnectionChanged(boolean isConnected);
    }
}