package com.alphagnfss.etr3;

import javafx.application.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Launcher {

	private static final Logger LOGGER = LogManager.getLogger(Launcher.class);

	public static void main(String[] args) {

		// Set UI
		Application.launch(Main.class, args);
	}
}