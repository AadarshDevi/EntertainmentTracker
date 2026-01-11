package com.alphagnfss.etr3.cli.v1;

import com.alphagnfss.etr3.api.v1.Api;
import com.alphagnfss.etr3.backend.data.Entertainment;
import com.alphagnfss.etr3.backend.data.EntertainmentStatus;
import com.alphagnfss.etr3.backend.data.EntertainmentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
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

//				if (string.startsWith("-p")) {
				if (string.startsWith("--post-name:")) {

					String name = string.replace("--post-name:", "").trim();

					System.out.print("Enter type-status: ");
					String ts = scanner.nextLine();
					EntertainmentType type = EntertainmentType.valueOf(ts.split("-")[0].trim().toUpperCase());
					EntertainmentStatus status = EntertainmentStatus.valueOf(ts.split("-")[1].trim().toUpperCase());

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

					int isSpecialNum = (isSpecial) ? 1 : 0;
					int isPilotNum = (isPilot) ? 1 : 0;
					int isFavoriteNum = (isFavorite) ? 1 : 0;

					System.out.print("SeasonId, EpisodeNum, Duration (S-E-D): ");
					String nums = scanner.nextLine();
					int seasonId = Integer.parseInt(nums.split("-")[0]);
					int episodeNum = Integer.parseInt(nums.split("-")[1]);
					int duration = Integer.parseInt(nums.split("-")[2]);

					LOGGER.info("Name > {}", name);
					LOGGER.info("Type > {}; Status > {}", type, status);
					LOGGER.info("LocalDate > {}", localDate);
					LOGGER.info("S, P, F > {} {} {}", isSpecial, isPilot, isFavorite);
					LOGGER.info("S, E, D > {}-{}-{}", seasonId, episodeNum, duration);

					ArrayList<String> tagList = new ArrayList<>(15);
					for (int i = 1; i <= 15; i++) {
						System.out.print("tags_" + i + ": ");
						String tagi = scanner.nextLine();
						if (tagi.isBlank()) break;
						tagList.add(tagi);
					}
					tagList.forEach(tag -> LOGGER.info("tag > {}", tag));
					String[] tags = tagList.toArray(new String[15]); // array with length 15

					Entertainment entertainment = Entertainment.builder()
							.name(name)
							.type(type)
							.date(localDate)
							.status(status)
							.isSpecial(isSpecial)
							.isPilot(isPilot)
							.isFavorite(isFavorite)
							.seasonId(seasonId)
							.episodeNum(episodeNum)
							.duration(duration)
							.tags(tags)
							.build();

					// test Entertainment Build
//					Entertainment entertainment = Entertainment.builder()
//							.name("Testing Entertainment via SQL Database")
//							.type(EntertainmentType.MOVIE)
//							.date(LocalDate.of(3000, 1, 1))
//							.status(EntertainmentStatus.UPCOMING)
//							.isSpecial(false)
//							.isPilot(false)
//							.isFavorite(false)
//							.seasonId(0)
//							.episodeNum(0)
//							.duration(0)
//							.tags(new String[]{
//									"testing", "app testing",
//									"", "", "", "", "", "", "",
//									"", "", "", "", "", ""
//							})
//							.build();

					boolean success = api.createEntertainment(entertainment);
					LOGGER.info("created: {}", success);

				} else if (string.startsWith("--delete-id:")) {
					int id = Integer.parseInt(string.replace("--delete-id:", "").trim());
					boolean success = api.deleteEntertainment(id);
					LOGGER.info("deleted: {}", success);
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
