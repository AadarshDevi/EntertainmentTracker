package com.alphagnfss.etr3.api.v1;

import com.alphagnfss.etr3.backend.data.Entertainment;
import com.alphagnfss.etr3.backend.data.VisualEntertainment;
import com.alphagnfss.etr3.backend.v1.Backend;

public class Api {

	public static Api api;
	private final Backend backend;

	public Api() {
		this.backend = Backend.getInstance();
	}

	public static Api getInstance() {
		if (api == null) api = new Api();
		return api;
	}

	public Entertainment getEntertainment(int id) {
		return backend.getEntertainment(id);
	}

	public VisualEntertainment getVisualEntertainment(int id) {
		return backend.getVisualEntertainment(id);
	}

	public Entertainment[] getEntertainments(String text) {
		return backend.getEntertainments(text);
	}

	public VisualEntertainment[] getVisualEntertainments(String text) {
		return backend.getVisualEntertainments(text);
	}

}
