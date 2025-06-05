package com.alphag947.CommandLineInterface.api;

public class DataAPIFactory {

    private static DataAPI dApi;

    public static DataAPI getDataApi() {
        if (dApi == null)
            dApi = new DataAPI();
        return dApi;
    }

}
