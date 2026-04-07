package com.alphagnfss.etr3.ui.v1.fxml;

import com.alphagnfss.etr3.ui.v1.controllers.Controller;
import com.alphagnfss.etr3.ui.v1.fxml.exceptions.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;

public class FXMLReader {
//	private static FXMLReader fxmlReader;
//
//	public static FXMLReader getInstance() {
//		if (fxmlReader == null) fxmlReader = new FXMLReader();
//		return fxmlReader;
//	}

	private static final Logger LOGGER = LogManager.getLogger(FXMLReader.class);

	public static FXMLPackage getPackage(String filepath) throws URLNotFoundException, FXMLLoaderNullException, PaneNotFoundException, PaneNullException, ControllerNullException, FXMLPackageNullException {
		URL url = FXMLReader.class.getResource("/com/alphagnfss/etr3/" + filepath);
		LOGGER.info("URL: {}", url);
		if (url == null) {
			throw new URLNotFoundException("Fxml - /com/alphagnfss/etr3/" + filepath);
		}
		FXMLLoader loader = new FXMLLoader(url);
		if (loader == null) {
			throw new FXMLLoaderNullException("URL");
		}

		Pane pane;
		try {
			pane = loader.load();
		} catch (IOException e) {
			throw new PaneNotFoundException(url.toString());
		}
		if (pane == null) throw new PaneNullException(url.toString());

		Controller controller = loader.getController();
		if (controller == null) throw new ControllerNullException(url.toString());

		FXMLPackage fxmlPackage = new FXMLPackage(pane, controller);
		if (fxmlPackage == null) throw new FXMLPackageNullException(url.toString());

		return fxmlPackage;
	}
}