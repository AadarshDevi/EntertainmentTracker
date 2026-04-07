package com.alphagnfss.etr3.ui.v1;

import com.alphagnfss.etr3.ui.v1.controllers.ControllerManager;
import com.alphagnfss.etr3.ui.v1.controllers.MainController;
import com.alphagnfss.etr3.ui.v1.fxml.FXMLPackage;
import com.alphagnfss.etr3.ui.v1.fxml.FXMLReader;
import com.alphagnfss.etr3.ui.v1.fxml.exceptions.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main extends Application {
	private static final Logger LOGGER = LogManager.getLogger(Main.class);

	@Override
	public void start(Stage stage) throws IOException {

		// mp: main fxml package
		FXMLPackage mp = null;
		try {
//			 ui/fxml/v1/mainframe/mainframe_v1.fxml
			mp = FXMLReader.getPackage("ui/fxml/v1/mode/mass/edit/v1/mass_edit_v1.fxml");
		} catch (FXMLPackageNullException e) {
			LOGGER.error("Main Package Null");
			LOGGER.error(e);
			System.exit(-1);
		} catch (URLNotFoundException | FXMLLoaderNullException |
				 PaneNotFoundException | PaneNullException |
				 ControllerNullException e) {
			LOGGER.error(e);
		}
		LOGGER.info("FXML Package: {}", mp);

		BorderPane mui = (BorderPane) mp.pane(); // mui: main ui
		MainController mnc = (MainController) mp.controller(); // mnc main controller
		ControllerManager.setMainController(mnc);

		stage.setScene(new Scene(mui));
		stage.show();
	}
}