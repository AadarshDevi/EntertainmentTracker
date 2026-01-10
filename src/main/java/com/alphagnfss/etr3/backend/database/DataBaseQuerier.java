package com.alphagnfss.etr3.backend.database;

import com.alphagnfss.etr3.backend.data.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class DataBaseQuerier {
	private final Connection connection;
	private final String tableName;

	public DataBaseQuerier(Connection connection, String tableName) {
		this.connection = connection;
		this.tableName = tableName;
	}

	public boolean checkId(int id) throws SQLException {
		String searchQuery = String.format("select id from %s where exists (select id from %s where id = %d)",
				tableName, tableName, id);
		PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
		ResultSet resultSet = preparedStatement.executeQuery();
		boolean exists = resultSet.next();
		resultSet.close();
		return exists;
	}

	public Entertainment getEntertainment(int id) throws SQLException, EntertainmentNotFoundException {

		if (!checkId(id)) {
			throw new EntertainmentNotFoundException(id);
		}

		String searchQuery = String.format("select * from %s where id = %d;", tableName, id);
		PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
		ResultSet resultSet = preparedStatement.executeQuery();

		int eid = resultSet.getInt("id");
		String name = resultSet.getString("name");
		EntertainmentType type = EntertainmentType.valueOf(resultSet.getString("type").toUpperCase());
		LocalDate date = LocalDate.parse(resultSet.getString("localDate"));

		EntertainmentStatus status = EntertainmentStatus.valueOf(resultSet.getString("status").toUpperCase());
		if (status.equals(EntertainmentStatus.UPCOMING) && LocalDate.now().isAfter(date)) {
			status = EntertainmentStatus.RELEASED;
		} else if (LocalDate.now().isBefore(date)) {
			status = EntertainmentStatus.UPCOMING;
		}

		boolean isSpecial = resultSet.getBoolean("isSpecial");
		boolean isPilot = resultSet.getBoolean("isPilot");
		boolean isFavorite = resultSet.getBoolean("isFavorite");

		int seasonId = resultSet.getInt("seasonId");
		int episodeNum = resultSet.getInt("episodeNum");
		int duration = resultSet.getInt("duration");

		ArrayList<String> tags = new ArrayList<>();
		for (int i = 1; i <= 15; i++) {
			String tag = resultSet.getString("tags_" + i);
			if (tag.isBlank()) continue;
			System.out.println("tag_" + i + ": " + tag);
			tags.add(tag);
		}

		Entertainment entertainment = Entertainment.builder()
				.id(eid)
				.name(name)
				.type(type)
				.date(date)

				.status(status)
				.isSpecial(isSpecial)
				.isPilot(isPilot)
				.isFavorite(isFavorite)

				.seasonId(seasonId)
				.episodeNum(episodeNum)
				.duration(duration)

				.tags(tags.toArray(new String[0]))

				.build();

		resultSet.close();
		return entertainment;
	}

	public VisualEntertainment getVisualEntertainment(int id) throws SQLException, EntertainmentNotFoundException {
		return new VisualEntertainment(getEntertainment(id));
	}

	// todo
	public Entertainment[] getEntertainments(String text) {
		return null;
	}

	public VisualEntertainment[] getVisualEntertainment(String text) {
		Entertainment[] entertainments = getEntertainments(text);

		if (entertainments.length == 0) return null;

		ArrayList<VisualEntertainment> visualEntertainments = new ArrayList<>();
		Arrays.stream(entertainments).toList().forEach((entertainment) ->
				visualEntertainments.add(new VisualEntertainment(entertainment))
		);

		return visualEntertainments.toArray(new VisualEntertainment[0]);
	}
}
