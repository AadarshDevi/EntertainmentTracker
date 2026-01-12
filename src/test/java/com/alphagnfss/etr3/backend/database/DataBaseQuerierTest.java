package com.alphagnfss.etr3.backend.database;

import com.alphagnfss.etr3.backend.data.Entertainment;
import com.alphagnfss.etr3.backend.data.EntertainmentStatus;
import com.alphagnfss.etr3.backend.data.EntertainmentType;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;

public class DataBaseQuerierTest extends TestCase {
	protected Entertainment entertainment;
	protected DataBaseQuerier dataBaseQuerier;

	// create data obj
	@Override
	protected void setUp() throws Exception {
		entertainment = Entertainment.builder()
				.name("ETR3 Testing: Entertainment via SQL Database")
				.type(EntertainmentType.MOVIE)
				.date(LocalDate.of(3000, 1, 1))
				.status(EntertainmentStatus.UPCOMING)
				.isSpecial(false)
				.isPilot(false)
				.isFavorite(false)
				.seasonId(0)
				.episodeNum(0)
				.duration(0)
				.tags(new String[]{
						"testing", "app testing",
						"", "", "", "", "", "", "",
						"", "", "", "", "", ""
				})
				.build();

		final Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/Programming/Java/Projects/Entertainment_Tracker/database.entertainmenttracker.sqlite");
		dataBaseQuerier = new DataBaseQuerier(connection, "entertainment_table");
	}

	// add data obj
	@Override
	protected void tearDown() throws Exception {
		boolean success = dataBaseQuerier.createEntertainment(entertainment);
		assertTrue(success);
	}
}
