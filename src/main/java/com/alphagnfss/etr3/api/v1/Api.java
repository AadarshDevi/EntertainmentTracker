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

	// HTTP : GET : Entertainment : id
	public Entertainment getEntertainment(int id) {
		return backend.getEntertainment(id);
	}

	// HTTP : GET : VisualEntertainment : id
	public VisualEntertainment getVisualEntertainment(int id) {
		return backend.getVisualEntertainment(id);
	}

	// HTTP : GET : Entertainments : String
	public Entertainment[] getEntertainments(String text) {
		return backend.getEntertainments(text);
	}

	// HTTP : GET : VisualEntertainments : String
	public VisualEntertainment[] getVisualEntertainments(String text) {
		return backend.getVisualEntertainments(text);
	}

	// HTTP : POST : Entertainment : Entertainment
	public boolean createEntertainment(Entertainment entertainment) {
		return backend.createEntertainment(entertainment);
	}

	// HTTP : Delete : Entertainment : id
	public boolean deleteEntertainment(int id) {
		return backend.deleteEntertainment(id);
	}

}
