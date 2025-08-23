package com.alphag947.backend.settings;

public class SettingsFactory {

    /**
     * The Settings object that has a HashMap of all settings data
     *
     * @see Settings
     */
    private static Settings settings;

    /**
     * Stores how many Settings object is requested
     */
    private static int requestCount = 0;

    /**
     * the method will return the Settings obj after checking the number of times
     * it was requested. if it was requested more than 1 time, it will block access
     * to get the object.
     *
     * @return return the Settings obj
     * @throws SettingsAccessDeniedException
     * @see Settings
     * @see SettingsAccessDeniedException
     */
    public static Settings getSettings() throws SettingsAccessDeniedException {
        if (requestCount > 1) throw new SettingsAccessDeniedException();
        requestCount++;
        if (settings == null) settings = new Settings();

        return settings;
    }
}
