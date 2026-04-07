package com.alphagnfss.etr3.ui.v1.controllers;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ControllerManager {

	@Setter
	@Getter
	private static MainController mainController;

	private static ControllerManager controllerManager;

	public static ControllerManager getInstance() {
		if (mainController == null) controllerManager = new ControllerManager();
		return controllerManager;
	}


}