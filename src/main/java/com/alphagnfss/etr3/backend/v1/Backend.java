package com.alphagnfss.etr3.backend.v1;

import com.alphagnfss.etr3.backend.database.DataBaseManager;
import com.alphagnfss.etr3.backend.database.DataBaseQuerier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class Backend {

	private static final Logger LOGGER = LogManager.getLogger(Backend.class);
	private static Backend backend;

	public Backend() {

		InternalConfig internalConfig = null;
		try {
			internalConfig = InternalConfig.getInstance();
			LOGGER.info("InternalConfig initialized");
		} catch (IOException e) {
			LOGGER.error(e);
			System.exit(0);
		}
		if (internalConfig == null) {
			LOGGER.error("Unable to load internal config");
			System.exit(0);
		}

		DataBaseManager dataBaseManager = null;
		try {
			dataBaseManager = new DataBaseManager(internalConfig.getConfig("dbpath"));
			LOGGER.info("Database Found");
		} catch (SQLException e) {
			LOGGER.error(e);
			System.exit(0);
		}

		try {
			boolean isConnected = dataBaseManager.isConnected();
			LOGGER.info("Database Connected: {}", isConnected);
		} catch (SQLException e) {
			LOGGER.error(e);
			System.exit(0);
		}

		System.out.println();
		DataBaseQuerier dataBaseQuerier = new DataBaseQuerier(dataBaseManager.getConnection(), internalConfig.getConfig("tablename"));

		try {
			dataBaseManager.close();
			LOGGER.info("Database Disconnected");
		} catch (SQLException e) {
			LOGGER.error(e);
			System.exit(0);
		}

		LOGGER.info("Closing app...");
		System.exit(0);
	}

	public static Backend getInstance() {
		if (backend == null) backend = new Backend();
		return backend;
	}
}
