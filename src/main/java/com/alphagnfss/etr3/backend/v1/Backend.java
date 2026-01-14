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

		dataBaseQuerier = new DataBaseQuerier(dataBaseManager.getConnection(), internalConfig.getConfig("tablename"));

	}

	public static Backend getInstance() {
		if (backend == null) backend = new Backend();
		return backend;
	}

	// HTTP : GET : Entertainment : id
	public Entertainment getEntertainment(int id) {
		try {
			return dataBaseQuerier.getEntertainment(id);
		} catch (SQLException e) {
			LOGGER.error("Unable to get Entertainment from database");
			LOGGER.error(e);
			return null;
		} catch (EntertainmentNotFoundException e) {
			return null;
		} catch (EntertainmentDoesNotExistException e) {
			LOGGER.error("Entertainment does not exist");
			LOGGER.error(e);
			return null;
		}
	}

	// HTTP : GET : VisualEntertainment : id
	public VisualEntertainment getVisualEntertainment(int id) {
		try {
			return dataBaseQuerier.getVisualEntertainment(id);
		} catch (SQLException e) {
			LOGGER.error("Unable to get VirtualEntertainment from database");
			LOGGER.error(e);
			return null;
		} catch (EntertainmentNotFoundException e) {
			return null;
		} catch (EntertainmentDoesNotExistException e) {
			LOGGER.error("VirtualEntertainment does not exist");
			LOGGER.error(e);
			return null;
		}
	}

	// HTTP : GET : Entertainments : String
	public Entertainment[] getEntertainments(String text) {
		try {
			return dataBaseQuerier.getEntertainments(text);
		} catch (SQLException e) {
			LOGGER.error(e);
			return null;
		}
	}

	// HTTP : GET : VisualEntertainments : String
	public VisualEntertainment[] getVisualEntertainments(String text) {
		try {
			return dataBaseQuerier.getVisualEntertainments(text);
		} catch (SQLException e) {
			return null;
		}
	}

	// HTTP : POST : Entertainment : Entertainment
	public boolean createEntertainment(Entertainment entertainment) {
		try {
			return dataBaseQuerier.createEntertainment(entertainment);
		} catch (SQLException e) {
			LOGGER.error("Unable to create Entertainment");
			LOGGER.error(e);
			return false;
		}
	}

	// HTTP : DELETE : Entertainment : id
	public boolean deleteEntertainment(int id) {
		try {
			return dataBaseQuerier.deleteEntertainment(id);
		} catch (SQLException e) {
			LOGGER.error("Unable to delete Entertainment");
			LOGGER.error(e);
			return false;
		}
	}

	// HTTP : PATCH : Entertainment : Entertainment
	public boolean updateEntertainment(Entertainment updatedEntertainment) {
		try {
			return dataBaseQuerier.updateEntertainment(updatedEntertainment);
		} catch (SQLException | EntertainmentDoesNotExistException | EntertainmentNotFoundException e) {
			LOGGER.error("Unable to update Entertainment");
			LOGGER.error(e);
			return false;
		}
	}

	// HTTP : PUT : Entertainment : Entertainment
	public boolean replaceEntertainment(Entertainment updatedEntertainment) {
		try {
			return dataBaseQuerier.replaceEntertainment(updatedEntertainment);
		} catch (SQLException e) {
			LOGGER.error("Unable to replace Entertainment");
			LOGGER.error(e);
			return false;
		}
	}
}