package com.alphagnfss.etr3.cli.v1;

import com.alphagnfss.etr3.api.v1.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
			try {
				int eid = Integer.parseInt(scanner.nextLine());
				System.out.println(api.getEntertainment(eid));
				System.out.println(api.getVisualEntertainment(eid));
			} catch (NumberFormatException e) {
				LOGGER.error("Invalid eid enter");
			}
		}
	}
}
