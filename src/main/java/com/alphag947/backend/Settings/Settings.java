package com.alphag947.backend.Settings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.alphag947.backend.logging.LoggerFactory;

public class Settings {

    // ../../../../../resources
    private String READ_FILEPATH_SETTINGS = "/com/alphag947/data/data/settings.txt";
    private HashMap<String, String> settings;

    public Settings() {

        settings = new HashMap<>();
        InputStream is = Settings.class.getResourceAsStream(READ_FILEPATH_SETTINGS);
        if (is == null)
            LoggerFactory.getLogger().err(new Exception("\"is\" is null"));

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {

            String line;

            while ((line = fileReader.readLine()) != null) {

                if (!(line.startsWith("//"))) {
                    settings.put(line.split("<:>")[0], line.split("<:>")[1]);
                    LoggerFactory.getLogger().dbg(line);
                }
            }

            LoggerFactory.getConsoleLogger().log(this, "\"settings.txt\" successfully read.");

            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return settings.get(key);
    }
}
