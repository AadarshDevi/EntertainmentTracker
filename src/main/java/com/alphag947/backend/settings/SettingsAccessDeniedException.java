package com.alphag947.backend.settings;

public class SettingsAccessDeniedException extends Exception {

    /**
     * This error is thrown when Settings object is requested more than 1 time.
     *
     * @see Settings
     */
    public SettingsAccessDeniedException() {
        super("Settings cannot be accessed because it is called more than once");
    }
}
