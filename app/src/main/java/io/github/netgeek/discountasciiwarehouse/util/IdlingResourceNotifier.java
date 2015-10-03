package io.github.netgeek.discountasciiwarehouse.util;

import android.util.Log;

/**
 * Notifies IdlingResourceManager when resources are busy and when they return to idle,
 * allowing for the IdlingResourceManager to notify Espresso when tests are ready to continue.
 */
public class IdlingResourceNotifier {

    private static IdlingResourceNotifier instance = null;
    private IdleListener idleListener;

    private IdlingResourceNotifier() {}

    /**
     * Gets the singleton instance of this class.
     *
     * @return the singleton instance object
     */
    public static synchronized IdlingResourceNotifier getInstance() {
        if (instance == null) {
            try {
                instance = new IdlingResourceNotifier();
            } catch (Exception e) {
                Log.e("Instance Error", e.toString());
            }
        }
        return instance;
    }

    /**
     * Idle interface to be implemented by IdlingResourceManager
     */
    public interface IdleListener {
        void onIdle();
        void onBusy();

    }

    public void setIdleListener(IdleListener idleListener) {
        this.idleListener = idleListener;
    }

    /**
     * Notifies the registered listener that the thread is idle or busy
     *
     * @param isIdle if the resource will be set to idle or busy
     */
    public void notifyIdle(Boolean isIdle) {
        if(idleListener != null){
            if (isIdle) {
                idleListener.onIdle();
            } else {
                idleListener.onBusy();
            }
        }
    }
}

