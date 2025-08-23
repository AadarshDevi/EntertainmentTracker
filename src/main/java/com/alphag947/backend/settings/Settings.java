package com.alphag947.backend.settings;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

public class Settings {

    /**
     * This contains all the data for settings and can be retrieved by asking with
     * a key
     */
    private final HashMap<SettingKey, String> settings;

    /**
     * This reads the settings file when creating the object. there will always be
     * a single instance of this class
     */
    public Settings() {

        /*
         * this is the filepath of the settings file.
         */
        final String READ_FILEPATH_SETTINGS = "/com/alphag947/data/data/settings.txt";

        /*
         * this stores all the settings for the app.
         */
        settings = new HashMap<>();

        /*
         * The logger for the Setting constructor
         */
        Logger LOGGER = Logger.getLogger(Settings.class);

        /*
         * This is to read the settings file.
         * fileReader is automatically closed by try-catch
         */
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Settings.class.getResourceAsStream(READ_FILEPATH_SETTINGS)), "UTF-8"))) {

            String line;

            while ((line = fileReader.readLine()) != null) {

                if (!(line.startsWith("//"))) {
                    settings.put(
                            SettingKey.valueOf(line.split("<:>")[0]),
                            line.split("<:>")[1]
                    );

                    LOGGER.info(line);
                }
            }
            LOGGER.info("\"settings.txt\" successfully read.");

        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public String getValue(SettingKey sk) {
        return settings.get(sk);
    }
}
