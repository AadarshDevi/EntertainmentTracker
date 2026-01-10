package com.alphagnfss.etr3.backend.v1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class InternalConfig {

	private static final Logger LOGGER = LogManager.getLogger(InternalConfig.class);
	private static InternalConfig internalConfig;
	private final Properties properties;

	public InternalConfig() throws IOException {
		properties = new Properties();
		properties.load(getClass().getResourceAsStream("/com/alphagnfss/etr3/internals/configs/entertainmenttracker.configs"));
	}

	public static InternalConfig getInstance() throws IOException {
		if (internalConfig == null) internalConfig = new InternalConfig();
		return internalConfig;
	}

	public String getConfig(String configName) {
		LOGGER.info("{}={}", configName, properties.getProperty(configName));
		return properties.getProperty(configName);
	}
}
