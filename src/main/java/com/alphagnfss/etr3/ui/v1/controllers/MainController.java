package com.alphagnfss.etr3.ui.v1.controllers;

import com.alphagnfss.etr3.api.v1.Api;
import com.alphagnfss.etr3.backend.data.VisualEntertainment;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {
	@FXML
	public ListView<String> homeList;

	@FXML
	public void initialize() {
		ArrayList<VisualEntertainment> data = new ArrayList<>(List.of(Api.getInstance().getVisualEntertainments(1, 536)));
		data.forEach(entertainment -> {
			homeList.getItems().add(entertainment.name());
		});
	}
}