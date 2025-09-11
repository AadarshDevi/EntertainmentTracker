package com.alphag947.CommandLineInterface;

import java.util.ArrayList;

import com.alphag947.api.Api;
import com.alphag947.api.ApiFactory;
import com.alphag947.backend.entertainment.enumeration.EntertainmentStatus;
import com.alphag947.backend.logging.ConsoleLogger;

public class DataAPI {

    private Api api;
    private ConsoleLogger logger;

    public DataAPI() {
        api = ApiFactory.getApi();
        logger = new ConsoleLogger();
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
        ArrayList<String> list = api.getEntertainmentByPrimaryStatus(primaryStatus);
        return list;
    }

    // public Entertainment getEntertainmentById(int entertainmentId) {
    // try {
    // return api.getEntertainmentById(entertainmentId);
    // } catch (EntertainmentNotFoundException e) {
    // logger.err(new EntertainmentNotFoundException(entertainmentId));
    // }
    // return Entertainment.EMPTY;
    // }
}
