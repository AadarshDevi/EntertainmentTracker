package com.alphagnfss.etr3;

import com.alphagnfss.etr3.data.Movie;
import javafx.application.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Launcher {

    private static final Logger LOGGER = LogManager.getLogger(Launcher.class);

    public static void main(String[] args) {

        Movie movie = new Movie();
        LOGGER.info(movie.getMessage());

        // Set UI
        Application.launch(Main.class, args);
    }
}