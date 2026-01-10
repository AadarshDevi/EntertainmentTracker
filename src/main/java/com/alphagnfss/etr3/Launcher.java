package com.alphagnfss.etr3;

import com.alphagnfss.etr3.backend.v1.Backend;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Launcher {

	private static final Logger LOGGER = LogManager.getLogger(Launcher.class);

	public static void main(String[] args) {
		// Set Backend
		Backend backend = Backend.getInstance();
		LOGGER.info("Backend initialized");

		// Set Api

		// Set CLI

		// Set UI
//		Application.launch(Main.class, args);
	}
}
