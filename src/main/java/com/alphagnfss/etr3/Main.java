package com.alphagnfss.etr3;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main extends Application {
	private static final Logger LOGGER = LogManager.getLogger(Main.class);

	@Override
	public void start(Stage stage) throws IOException {
		LOGGER.info("Starting Main");
	}
}