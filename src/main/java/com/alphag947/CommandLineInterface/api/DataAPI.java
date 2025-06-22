package com.alphag947.CommandLineInterface.api;

import java.util.ArrayList;

import com.alphag947.api.AppApi;
import com.alphag947.api.AppApiFactory;
import com.alphag947.backend.entertainment.EntertainmentStatus;

public class DataAPI {

    AppApi api;

    public DataAPI() {
        api = AppApiFactory.getApi();
    }

    public EntertainmentStatus getEntertainmentPrimaryStatus(int base_num, int child_num) {
        EntertainmentStatus primary_status = api.getEntertainmentPrimaryStatusByIndex(base_num, child_num);
        if (primary_status == EntertainmentStatus.NULL)
            System.out.println("[Error] > primary_status is 0");
        return primary_status;
    }

    public void setEntertainmentPrimaryStatus(int parent_value, int child_value,
            EntertainmentStatus new_primary_status) {
        api.setEntertainmentPrimaryStatus(parent_value, child_value, new_primary_status);
    }

    public ArrayList<String> getEntertainmentByPrimartStatus(EntertainmentStatus primaryStatus) {
        ArrayList<String> list = api.getEntertainmentByPrimartStatus(primaryStatus);
        return list;
    }

}
