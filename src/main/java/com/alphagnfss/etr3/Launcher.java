package com.alphagnfss.etr3;

import com.alphagnfss.etr3.api.v1.Api;
import com.alphagnfss.etr3.backend.v1.Backend;
import com.alphagnfss.etr3.cli.v1.Cli;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Launcher {

	private static final Logger LOGGER = LogManager.getLogger(Launcher.class);

	public static void main(String[] args) {
		// Set Backend
		Backend backend = Backend.getInstance();
		LOGGER.info("Backend initialized");

		// Set Api
		Api api = Api.getInstance();
		LOGGER.info("Api initialized");

		// Set CLI
		Cli cli = Cli.getInstance();
		LOGGER.info("Cli initialized");

		cli.start();

		// Set UI
//		Application.launch(Main.class, args);
	}
}
