package com.alphag947.backend.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Settings {

    // public static int moduleID = 0;
    // public static int backupNum = 1;
    // public static String READ_FILEPATH_DATA; // = "";
    private String READ_FILEPATH_SETTINGS = "D:/Projects/Entertainment_Tracker/build_3/entertainment_tracker/src/main/data/com/alphag947/data/settings.txt"; // =
    HashMap<String, String> settings; // "";

    public Settings() {

        settings = new HashMap<>();
        ArrayList<String[]> parsedData = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(
                new FileReader(new File(READ_FILEPATH_SETTINGS)))) {

            String line;

            while ((line = fileReader.readLine()) != null) {

                if (!(line.startsWith("//"))) {
                    settings.put(line.split("<:>")[0], line.split("<:>")[1]);
                }
            }
        } catch (

        FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // File readData = new File("D:/Projects/Entertainment_Tracker/data/data.txt");
        // File writeData = new File("src/main/data/com/alphag947/data.txt");

        // BufferedWriter writer = new BufferedWriter(new FileWriter(writeData));
        // String line;
        // int e_id = 1;
        // while ((line = reader.readLine()) != null) {
        // if (line.startsWith("//"))
        // writer.write(line + "\n");
        // else {
        // writer.write(e_id + "<##>" + line + "\n");
        // e_id++;
        // }

        // System.out.println(e_id + "<##>" + line);
        // }

        // reader.close();
        // writer.close();

        // System.exit(0);
    }

    public String getDataPath() {
        return settings.get("READ_FILEPATH_DATA");
    }
}
