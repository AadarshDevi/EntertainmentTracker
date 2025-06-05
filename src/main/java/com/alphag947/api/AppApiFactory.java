package com.alphag947.api;

public class AppApiFactory {
    private static AppApi api;

    public static AppApi getApi() {
        if (api == null)
            api = new AppApi();
        return api;
    }
}
