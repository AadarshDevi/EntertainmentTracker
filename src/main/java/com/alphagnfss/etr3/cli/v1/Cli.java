package com.alphagnfss.etr3.cli.v1;

import com.alphagnfss.etr3.api.v1.Api;
import com.alphagnfss.etr3.backend.data.Entertainment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Scanner;

public class Cli {
	private static final Logger LOGGER = LogManager.getLogger(Cli.class);
	public static Cli cli;
	private final Api api;
	private boolean running;

	public Cli(Api api) {
		this.api = api;
		this.running = false;
	}

	public static Cli getInstance() {
		if (cli == null) cli = new Cli(Api.getInstance());
		return cli;
	}

	public void start() {
		running = true;
		LOGGER.info("Cli starting...");
		run();
	}

	private void run() {
		Scanner scanner = new Scanner(System.in);
		LOGGER.info("Cli running");
		while (running) {
			System.out.print("Enter eid > ");
			String string = scanner.nextLine();
			try {
				int eid = Integer.parseInt(string);
				LOGGER.info(api.getEntertainment(eid));
			} catch (NumberFormatException e) {
				LOGGER.error("Invalid eid enter");

				if (string.startsWith("--post-name:")) {

					String name = string.replace("--post-name:", "").trim();

					System.out.print("Enter type-status: ");
					String ts = scanner.nextLine();
					String type = ts.split("-")[0].trim();
					String status = ts.split("-")[1].trim();

					System.out.print("release date (yyyy-MM-dd): ");
					String dateText = scanner.nextLine();
					LocalDate localDate = LocalDate.of(Integer.parseInt(dateText.split("-")[0]), Integer.parseInt(dateText.split("-")[1]), Integer.parseInt(dateText.split("-")[2]));

					System.out.print("Special, Pilot, Favorite (S-P-F): ");
					String booleans = scanner.nextLine();
					boolean isSpecial = false;
					boolean isPilot = false;
					boolean isFavorite = false;
					if (booleans.split("-")[0].equalsIgnoreCase("1")) isSpecial = true;
					if (booleans.split("-")[1].equalsIgnoreCase("1")) isPilot = true;
					if (booleans.split("-")[2].equalsIgnoreCase("1")) isFavorite = true;

					LOGGER.info("Name > {}", name);
					LOGGER.info("Type > {}; Status > {}", type, status);
					LOGGER.info("LocalDate > {}", localDate);
					LOGGER.info("S, P, F > {}-{}-{}", isSpecial, isPilot, isFavorite);


				} else {
					Entertainment[] entertainments = api.getEntertainments(string);
					if (entertainments == null || entertainments.length == 0) LOGGER.error("No entertainments found");
					else for (int i = 0; i < entertainments.length; i++)
						LOGGER.info("{}: {}", (i + 1), entertainments[i]);
				}
			}
		}
	}
}
