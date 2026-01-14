package com.alphagnfss.etr3.backend.database;

import com.alphagnfss.etr3.backend.data.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>
 * DataBaseQuerier does all the sql querying to the database. this helps to organise
 * sql statements. More http get methods MIGHT be added for [Advanced Search].
 * </p>
 * <p>
 * Format of docs > HTTP : HTTP Method : Target Object : Arguments
 * </p>
 * <p>
 * HTTP Method does its http process on the Target Object that is found in the database using the Arguments
 * </p>
 *
 * @see Connection
 * @see Entertainment
 * @see VisualEntertainment
 */
public class DataBaseQuerier {
	private static final Logger LOGGER = LogManager.getLogger(DataBaseQuerier.class);
	private final Connection connection;
	private final String tableName;

	/**
	 * Get the connection to the database and the table name for querying.
	 *
	 * @param connection the connection to the database
	 * @param tableName  the name of the table
	 */
	public DataBaseQuerier(Connection connection, String tableName) {
		this.connection = connection;
		this.tableName = tableName;
	}

	/**
	 * checks if the given id exists in the database
	 *
	 * @param id id to be checked
	 * @return if the id in the database exists or not
	 * @throws SQLException for sql statements
	 */
	public boolean checkId(int id) throws SQLException {
		String searchQuery = String.format("select id from %s where exists (select id from %s where id = %d)",
				tableName, tableName, id);
		PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
		ResultSet resultSet = preparedStatement.executeQuery();
		boolean exists = resultSet.next();
		resultSet.close();
		return exists;
	}

	/**
	 * HTTP : GET : Entertainment : id
	 *
	 * @param id entertainment id
	 * @return Entertainment object with the requested id
	 * @throws SQLException                       for sql statements
	 * @throws EntertainmentNotFoundException     when entertainment with id is not found
	 * @throws EntertainmentDoesNotExistException when entertainment id does not exist
	 */
	public Entertainment getEntertainment(int id) throws SQLException, EntertainmentNotFoundException, EntertainmentDoesNotExistException {

		if (!checkId(id)) {
			throw new EntertainmentDoesNotExistException(id);
		}

		String searchQuery = String.format("select * from %s where id = %d;", tableName, id);
		PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
		ResultSet resultSet = preparedStatement.executeQuery();

		Entertainment entertainment = getEntertainment(resultSet);

		resultSet.close();
		return entertainment;
	}

	/**
	 * HTTP : GET : VisualEntertainment : id
	 *
	 * @param id entertainment id
	 * @return VisualEntertainment object with the requested id
	 * @throws SQLException                       for sql statements
	 * @throws EntertainmentNotFoundException     when entertainment with id is not found
	 * @throws EntertainmentDoesNotExistException when entertainment id does not exist
	 */
	public VisualEntertainment getVisualEntertainment(int id) throws SQLException, EntertainmentNotFoundException, EntertainmentDoesNotExistException {
		return new VisualEntertainment(getEntertainment(id));
	}

	/**
	 * HTTP : GET : Entertainment[] : String
	 *
	 * @param text the text that each data will have in any shape or form
	 * @return data containing the specific text
	 * @throws SQLException for sql statements
	 */
	public Entertainment[] getEntertainments(String text) throws SQLException {
		String sqlText = "%" + text + "%";
		LOGGER.debug("sqlText: {}", sqlText);
		String searchQuery = String.format(
				"select * from %s where " +
						"name like ? or " +
						"tags_1 like ? or tags_2 like ? or tags_3 like ? or tags_4 like ? or " +
						"tags_5 like ? or tags_6 like ? or tags_7 like ? or tags_8 like ? or " +
						"tags_9 like ? or tags_10 like ? or tags_11 like ? or tags_12 like ? or " +
						"tags_13 like ? or tags_14 like ? or tags_15 like ?;",
				tableName
		);
		LOGGER.debug("sql: {}", searchQuery);

		PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);

		for (int i = 1; i <= 15; i++) {
			preparedStatement.setString(i, sqlText);
		}

		ResultSet resultSet = preparedStatement.executeQuery();

		Entertainment[] entertainments = getEntertainments(resultSet);

		resultSet.close();
		return entertainments;
	}

	/**
	 * HTTP : GET : VisualEntertainment[] : String
	 *
	 * @param text the text that each data will have in any shape or form
	 * @return data containing the specific text
	 * @throws SQLException for sql statements
	 */
	public VisualEntertainment[] getVisualEntertainments(String text) throws SQLException {
		Entertainment[] entertainments = getEntertainments(text);

		if (entertainments == null) return null;
		if (entertainments.length == 0) return null;

		ArrayList<VisualEntertainment> visualEntertainments = new ArrayList<>();
		Arrays.stream(entertainments).toList().forEach((entertainment) ->
				visualEntertainments.add(new VisualEntertainment(entertainment))
		);

		return visualEntertainments.toArray(new VisualEntertainment[0]);
	}

	/**
	 * HTTP : GET : Entertainments : min id, max id
	 *
	 * @param min the min entertainment id
	 * @param max the max entertainment id
	 * @return data that are in the range of the given entertainment ids
	 * @throws SQLException for sql statements
	 */
	public Entertainment[] getEntertainments(int min, int max) throws SQLException {
		LOGGER.debug("range: [{}, {}]", min, max);
		String searchQuery = String.format(
				"select * from %s where id >= ? and id <= ? and not type = \"episode\";",
				tableName
		);
		LOGGER.debug("sql: {}", searchQuery);

		PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
		preparedStatement.setInt(1, min);
		preparedStatement.setInt(2, max);

		ResultSet resultSet = preparedStatement.executeQuery();

		Entertainment[] entertainments = getEntertainments(resultSet);

		resultSet.close();
		return entertainments;
	}

	/**
	 * HTTP : GET : VisualEntertainments : min id, max id
	 *
	 * @param min the min entertainment id
	 * @param max the max entertainment id
	 * @return data that are in the range of the given entertainment ids
	 * @throws SQLException for sql statements
	 */
	public VisualEntertainment[] getVisualEntertainments(int min, int max) throws SQLException {
		Entertainment[] entertainments = getEntertainments(min, max);

		if (entertainments == null) return null;
		if (entertainments.length == 0) return null;

		ArrayList<VisualEntertainment> visualEntertainments = new ArrayList<>();
		Arrays.stream(entertainments).toList().forEach((entertainment) ->
				visualEntertainments.add(new VisualEntertainment(entertainment))
		);

		return visualEntertainments.toArray(new VisualEntertainment[0]);
	}

	/**
	 * Convert : Entertainment : ResultSet : min id, max id
	 *
	 * @param resultSet the set of rows received from database
	 * @return data that was in the resultSet
	 * @throws SQLException for sql statements
	 */
	private Entertainment getEntertainment(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
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

		String[] tags = new String[15];
		Arrays.fill(tags, "");
		for (int i = 1; i <= 15; i++) {
			String tag = resultSet.getString("tags_" + i);
			tags[i - 1] = tag;
		}

		Entertainment entertainment;
		entertainment = Entertainment.builder()
				.id(id).name(name).type(type).date(date)
				.status(status).isSpecial(isSpecial).isPilot(isPilot).isFavorite(isFavorite)
				.seasonId(seasonId).episodeNum(episodeNum).duration(duration)
				.tags(tags)
				.build();

		return entertainment;
	}

	/**
	 * Convert : Entertainment[] : ResultSet : min id, max id
	 *
	 * @param resultSet the set of rows received from database
	 * @return data that was in the resultSet
	 * @throws SQLException for sql statements
	 */
	private Entertainment[] getEntertainments(ResultSet resultSet) throws SQLException {

		final ArrayList<Entertainment> entertainments = new ArrayList<>();

		while (resultSet.next()) {
			Entertainment entertainment = getEntertainment(resultSet);
			entertainments.add(entertainment);
		}

		return entertainments.toArray(new Entertainment[0]);
	}

	/**
	 * HTTP : POST : Entertainment : Entertainment
	 *
	 * @param entertainment the data to be created
	 * @return if data was created
	 * @throws SQLException for sql statements
	 */
	public boolean createEntertainment(Entertainment entertainment) throws SQLException {
		String insertQuery = String.format("insert into %s (" +
				"name, type, localDate, " +
				"status, isSpecial, isPilot, isFavorite, " +
				"seasonId, episodeNum, duration, " +

				"tags_1, tags_2, tags_3, tags_4, tags_5, tags_6, " +
				"tags_7, tags_8, tags_9, tags_10, tags_11, tags_12, " +
				"tags_13, tags_14, tags_15)" +
				"values (" +
				"?, ?, ?, " +
				"?, ?, ?, ?, " +
				"?, ?, ?, " +
				"?, ?, ?, ?, ?, ?, " +
				"?, ?, ?, ?, ?, ?, " +
				"?, ?, ?)", tableName);

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, entertainment.name());
			preparedStatement.setString(2, entertainment.type().name().toLowerCase());
			preparedStatement.setString(3, entertainment.date().toString());
			preparedStatement.setString(4, entertainment.status().name().toLowerCase());
			preparedStatement.setBoolean(5, entertainment.isSpecial());
			preparedStatement.setBoolean(6, entertainment.isPilot());
			preparedStatement.setBoolean(7, entertainment.isFavorite());
			preparedStatement.setInt(8, entertainment.seasonId());
			preparedStatement.setInt(9, entertainment.episodeNum());
			preparedStatement.setInt(10, entertainment.duration());
			String[] tags = entertainment.tags();
			for (int i = 1; i <= 15; i++) {
				String tag;
				try {
					tag = tags[i];
				} catch (NullPointerException | ArrayIndexOutOfBoundsException _) {
					tag = "";
				}
				LOGGER.debug("tag {}: {}", i, tag);
				preparedStatement.setString((10 + i), tag);
			}

		} catch (SQLException e) {
			return false;
		}

		preparedStatement.executeUpdate();
		return true;
	}

	/**
	 * HTTP : DELETE : Entertainment : id
	 *
	 * @param id the data that will be deleted which has this id
	 * @return if data was deleted
	 * @throws SQLException for sql statements
	 */
	public boolean deleteEntertainment(int id) throws SQLException {
		String deleteQuery = String.format("delete from %s where id = ?", tableName);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, id);
		} catch (SQLException e) {
			return false;
		}

		preparedStatement.execute();
		return true;
	}

	/**
	 * patches the old data information using the new updated data
	 *
	 * @param updatedEntertainment the new data to be replaced the old data
	 * @return returns true if the data update was successful
	 * @throws SQLException                       for sql statements
	 * @throws EntertainmentDoesNotExistException when entertainment with id is not found
	 * @throws EntertainmentNotFoundException     when entertainment id does not exist
	 */
	public boolean updateEntertainment(Entertainment updatedEntertainment) throws SQLException, EntertainmentDoesNotExistException, EntertainmentNotFoundException {
		Entertainment entertainment = getEntertainment(updatedEntertainment.id());
		if (!entertainment.name().equals(updatedEntertainment.name())) {
			String updateQuery = String.format("update %s set name = ? where id = ?;", tableName);
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(2, updatedEntertainment.id());
			preparedStatement.setString(1, updatedEntertainment.name());
			int updated = preparedStatement.executeUpdate();
			if (updated == 1) LOGGER.info("Update: Name");
			else if (updated == 2) LOGGER.info("Update Failed: Name");
		}

		if (!entertainment.type().equals(updatedEntertainment.type())) {
			String updateQuery = String.format("update %s set type = ? where id = ?;", tableName);
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(2, updatedEntertainment.id());
			preparedStatement.setString(1, updatedEntertainment.type().toString());
			int updated = preparedStatement.executeUpdate();
			if (updated == 1) LOGGER.info("Update: Type");
			else if (updated == 2) LOGGER.info("Update Failed: Type");
		}

		if (!entertainment.date().equals(updatedEntertainment.date())) {
			String updateQuery = String.format("update %s set localDate = ? where id = ?;", tableName);
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(2, updatedEntertainment.id());
			preparedStatement.setString(1, updatedEntertainment.date().toString());
			int updated = preparedStatement.executeUpdate();
			if (updated == 1) LOGGER.info("Update: LocalDate");
			else if (updated == 2) LOGGER.info("Update Failed: LocalDate");
		}

		if (!entertainment.status().equals(updatedEntertainment.status())) {
			String updateQuery = String.format("update %s set status = ? where id = ?;", tableName);
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(2, updatedEntertainment.id());
			preparedStatement.setString(1, updatedEntertainment.status().toString());
			int updated = preparedStatement.executeUpdate();
			if (updated == 1) LOGGER.info("Update: Status");
			else if (updated == 2) LOGGER.info("Update Failed: Status");
		}

		if (entertainment.isSpecial() != updatedEntertainment.isSpecial()) {
			String updateQuery = String.format("update %s set isSpecial = ? where id = ?;", tableName);
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(2, updatedEntertainment.id());
			preparedStatement.setInt(1, updatedEntertainment.isSpecial() ? 1 : 0);
			int updated = preparedStatement.executeUpdate();
			if (updated == 1) LOGGER.info("Update: Special");
			else if (updated == 2) LOGGER.info("Update Failed: Special");
		}

		if (entertainment.isPilot() != updatedEntertainment.isPilot()) {
			String updateQuery = String.format("update %s set isPilot = ? where id = ?;", tableName);
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(2, updatedEntertainment.id());
			preparedStatement.setInt(1, updatedEntertainment.isPilot() ? 1 : 0);
			int updated = preparedStatement.executeUpdate();
			if (updated == 1) LOGGER.info("Update: Pilot");
			else if (updated == 2) LOGGER.info("Update Failed: Pilot");
		}

		if (entertainment.isFavorite() != updatedEntertainment.isFavorite()) {
			String updateQuery = String.format("update %s set isFavorite = ? where id = ?;", tableName);
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(2, updatedEntertainment.id());
			preparedStatement.setInt(1, updatedEntertainment.isFavorite() ? 1 : 0);
			int updated = preparedStatement.executeUpdate();
			if (updated == 1) LOGGER.info("Update: Favorite");
			else if (updated == 2) LOGGER.info("Update Failed: Favorite");
		}

		if (entertainment.seasonId() != updatedEntertainment.seasonId()) {
			String updateQuery = String.format("update %s set seasonId = ? where id = ?;", tableName);
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(2, updatedEntertainment.id());
			preparedStatement.setInt(1, updatedEntertainment.seasonId());
			int updated = preparedStatement.executeUpdate();
			if (updated == 1) LOGGER.info("Update: Season Id");
			else if (updated == 2) LOGGER.info("Update Failed: Season Id");
		}

		if (entertainment.episodeNum() != updatedEntertainment.episodeNum()) {
			String updateQuery = String.format("update %s set episodeNum = ? where id = ?;", tableName);
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(2, updatedEntertainment.id());
			preparedStatement.setInt(1, updatedEntertainment.episodeNum());
			int updated = preparedStatement.executeUpdate();
			if (updated == 1) LOGGER.info("Update: Episode Num");
			else if (updated == 2) LOGGER.info("Update Failed: Episode Num");
		}

		if (entertainment.duration() != updatedEntertainment.duration()) {
			String updateQuery = String.format("update %s set duration = ? where id = ?;", tableName);
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(2, updatedEntertainment.id());
			preparedStatement.setInt(1, updatedEntertainment.duration());
			int updated = preparedStatement.executeUpdate();
			if (updated == 1) LOGGER.info("Update: Duration");
			else if (updated == 2) LOGGER.info("Update Failed: Duration");
		}

		LOGGER.debug("New: {}, Old: {}", entertainment.tags().length, updatedEntertainment.tags().length);
		if (entertainment.tags().length != updatedEntertainment.tags().length) {
			for (int i = 0; i < updatedEntertainment.tags().length; i++) {
				updateTag(entertainment.id(), i, updatedEntertainment.tags()[i]);
			}
		} else {
			for (int i = 0; i < updatedEntertainment.tags().length; i++) {
				if (!entertainment.tags()[i].equals(updatedEntertainment.tags()[i])) {
					updateTag(entertainment.id(), i, updatedEntertainment.tags()[i]);
				}
			}
		}

		return true;
	}

	/**
	 * updates a tag for the data id given
	 *
	 * @param id         the id of the data that will have its tag will be replaced
	 * @param tagNum     the number of the tag that will be updated
	 * @param updatedTag the tag that will replace the old tag
	 * @throws SQLException for sql statements
	 */
	private void updateTag(int id, int tagNum, String updatedTag) throws SQLException {
		String updateQuery = String.format("update %s set %s = ? where id = ?;", tableName, "tags_" + (tagNum + 1));
		PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		preparedStatement.setInt(2, id);
		preparedStatement.setString(1, updatedTag);
		int updated = preparedStatement.executeUpdate();
		if (updated == 1) {
			LOGGER.info("Update: Id > {}; Tag {}", id, (tagNum + 1));
		} else if (updated == 2) {
			LOGGER.info("Update Failed: Id > {}; Tag {}", id, (tagNum + 1));
		} else {
			LOGGER.info("Update Unexpectedly Failed: Id > {}; Output {}; Tag {}", id, updated, (tagNum + 1));
		}
	}

	/**
	 * the entire data will be put (replacing) instead of the old data
	 *
	 * @param replacedEntertainment data to replace the old data
	 * @return returns true if the data replace was successful
	 */
	public boolean replaceEntertainment(Entertainment replacedEntertainment) throws SQLException {


		String replaceQuery = String.format("update %s set name = ?, type = ?, localDate = ?, status = ?, " +
				"isSpecial = ?, isPilot = ?, isFavorite = ?, " +
				"seasonId = ?, episodeNum = ?, duration = ?, " +
				"tags_1 = ?, tags_2 = ?, tags_3 = ?, tags_4 = ?, tags_5 = ?, tags_6 = ?, " +
				"tags_7 = ?, tags_8 = ?, tags_9 = ?, tags_10 = ?, tags_11 = ?, tags_12 = ?, " +
				"tags_13 = ?, tags_14 = ?, tags_15 = ? where id = ?;", tableName);

		PreparedStatement preparedStatement = connection.prepareStatement(replaceQuery);

		preparedStatement.setString(1, replacedEntertainment.name());
		preparedStatement.setString(2, replacedEntertainment.type().toString());
		preparedStatement.setString(3, replacedEntertainment.date().toString());
		preparedStatement.setString(4, replacedEntertainment.status().toString());
		preparedStatement.setInt(5, replacedEntertainment.isSpecial() ? 1 : 0);
		preparedStatement.setInt(6, replacedEntertainment.isPilot() ? 1 : 0);
		preparedStatement.setInt(7, replacedEntertainment.isFavorite() ? 1 : 0);
		preparedStatement.setInt(8, replacedEntertainment.seasonId());
		preparedStatement.setInt(9, replacedEntertainment.episodeNum());
		preparedStatement.setInt(10, replacedEntertainment.duration());
		for (int i = 1; i <= 15; i++) preparedStatement.setString((10 + i), replacedEntertainment.tags()[i - 1]);
		preparedStatement.setInt(26, replacedEntertainment.id());

		int updated = preparedStatement.executeUpdate();
		if (updated == 1) {
			LOGGER.info("Replaced: Id > {}", replacedEntertainment.id());
			return true;
		} else if (updated == 2) {
			LOGGER.info("Replaced Failed: Id > {}", replacedEntertainment.id());
			return false;
		} else {
			LOGGER.info("Replaced Unexpectedly Failed: Id > {}", replacedEntertainment.id());
			return false;
		}
	}
}