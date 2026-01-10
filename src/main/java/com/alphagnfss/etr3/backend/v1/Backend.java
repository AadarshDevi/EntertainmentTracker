package com.alphagnfss.etr3.backend.v1;

import com.alphagnfss.etr3.backend.data.Entertainment;
import com.alphagnfss.etr3.backend.data.EntertainmentDoesNotExistException;
import com.alphagnfss.etr3.backend.data.EntertainmentNotFoundException;
import com.alphagnfss.etr3.backend.data.VisualEntertainment;
import com.alphagnfss.etr3.backend.database.DataBaseManager;
import com.alphagnfss.etr3.backend.database.DataBaseQuerier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class Backend {

	private static final Logger LOGGER = LogManager.getLogger(Backend.class);
	private static Backend backend;
	private final DataBaseQuerier dataBaseQuerier;

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
		dataBaseQuerier = new DataBaseQuerier(dataBaseManager.getConnection(), internalConfig.getConfig("tablename"));

	}

	public static Backend getInstance() {
		if (backend == null) backend = new Backend();
		return backend;
	}

	public Entertainment getEntertainment(int id) {
		try {
			return dataBaseQuerier.getEntertainment(id);
		} catch (SQLException e) {
			// todo add logger
			return null;
		} catch (EntertainmentNotFoundException e) {
			return null;
		} catch (EntertainmentDoesNotExistException e) {
			// todo add logger for this too
			return null;
		}
	}

	public VisualEntertainment getVisualEntertainment(int id) {
		try {
			return dataBaseQuerier.getVisualEntertainment(id);
		} catch (SQLException e) {
			// todo add logger
			return null;
		} catch (EntertainmentNotFoundException e) {
			return null;
		} catch (EntertainmentDoesNotExistException e) {
			// todo add logger for this too
			return null;
		}
	}

	public Entertainment[] getEntertainments(String text) {
		return dataBaseQuerier.getEntertainments(text);
	}

	public VisualEntertainment[] getVisualEntertainments(String text) {
		return dataBaseQuerier.getVisualEntertainments(text);
	}
}
