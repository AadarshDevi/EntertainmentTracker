package com.alphagnfss.etr3.api.v1;

public class Api {

	public static Api api;

	public static Api getInstance() {
		if (api == null) api = new Api();
		return api;
	}
}
