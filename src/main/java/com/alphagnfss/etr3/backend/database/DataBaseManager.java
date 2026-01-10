package com.alphagnfss.etr3.backend.database;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DataBaseManager {
	private static final Logger LOGGER = LogManager.getLogger(DataBaseManager.class);
	private final Connection connection;

	public DataBaseManager(String databasePath) throws SQLException {
		String url = "jdbc:sqlite:" + databasePath;
		LOGGER.info("Database URL: {}", url);
		connection = DriverManager.getConnection(url);
	}

	public void close() throws SQLException {
		connection.close();
	}

	public boolean isConnected() throws SQLException {
		return connection.isClosed();
	}
}
