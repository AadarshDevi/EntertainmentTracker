package com.alphag947.backend.Settings;

public class SettingsFactory {

    private static Settings settings;

    public static Settings getSettings() {
        if (settings == null)
            settings = new Settings();
        return settings;
    }
}
