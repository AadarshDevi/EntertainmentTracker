package com.alphag947.api;

public class ApiFactory {
    private static Api api;

    public static Api getApi() {
        if (api == null)
            api = new Api();
        return api;
    }
}
