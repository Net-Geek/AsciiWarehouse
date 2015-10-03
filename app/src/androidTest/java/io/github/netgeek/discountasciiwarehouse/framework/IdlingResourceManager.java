package io.github.netgeek.discountasciiwarehouse.framework;

import android.support.test.espresso.IdlingResource;
import android.util.Log;

import io.github.netgeek.discountasciiwarehouse.util.IdlingResourceNotifier;

/**
 * Handles the idling of custom resources being tested, allowing for the main thread
 * to be set to busy or idle by Espresso. When the state of the IdlingResourceNotifier
 * is not idle, tests will only continue after the idle is set back to true. This allows
 * for tests to complete a multitude of tasks before continuing to the next execution of a view operation.
 */
public class IdlingResourceManager implements IdlingResource {

    private static IdlingResourceManager instance = null;
    private ResourceCallback resourceCallback;
    private boolean isIdle = true;

    private IdlingResourceManager() {
        IdlingResourceNotifier.IdleListener idleListener = new IdlingResourceNotifier.IdleListener() {
            @Override
            public void onIdle() {
                if (resourceCallback == null) {
                    return;
                }
                // Transition the resource to idle
                isIdle = true;
                resourceCallback.onTransitionToIdle();
            }

            @Override
            public void onBusy() {
                isIdle = false;
            }
        };
        IdlingResourceNotifier.getInstance().setIdleListener(idleListener);
    }

    /**
     * Gets the singleton instance of this class.
     *
     * @return the singleton instance object
     */
    public static synchronized IdlingResourceManager getInstance() {
        if (instance == null) {
            try {
                instance = new IdlingResourceManager();
            } catch (Exception e) {
                Log.e("Instance Error", e.toString());
            }
        }
        return instance;
    }

    /**
     * Provides the name of the idling resource manager
     * for logging and to ensure the resource is idempotent in registration.
     * <i>This is automatically called by Espresso.</i>
     *
     * @return the name of the idling resource manager
     */
    @Override
    public String getName() {
        return "IdlingResourceManager";
    }

    /**
     * Returns if the resource is currently idle.
     * <i>This is automatically called by Espresso.</i>
     *
     * @return if the thread is currently idle
     */
    @Override
    public boolean isIdleNow() {
        return isIdle;
    }

    /**
     * Registers the callback with the idling resource manager
     * so Espresso can be notified asynchronously when a resource
     * is idle.
     * <i>This is automatically called by Espresso.</i>
     *
     * @param callback Espresso's implementation of the resource callback
     */
    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback = callback;
    }
}
