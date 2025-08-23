package com.alphag947.backend.settings;

public class SettingsStreamNullException extends Exception {
    public SettingsStreamNullException() {
        super("Settings Reading InputStream is null");
    }
}
