package com.alphagnfss.etr3.backend.app;

public enum AppPermission {
    /**
     * This is the normal app permission and is there to make sure the user doesn't accidentally break the app or the data.
     */
    GUARDED,

    /**
     * The user knows what they are doing, and there are guard rails only for the critical infrastructure.
     * <p>
     * AppMode == DEVELOPER
     *
     * @see AppMode
     */
    FREE_SPIRIT,

    /**
     * User has no guard rails, 0, none.
     */
    BREAKER
}
