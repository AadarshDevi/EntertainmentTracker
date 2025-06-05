package com.alphag947.backend.searchengine;

import java.util.ArrayList;

import com.alphag947.backend.entertainment.Entertainment;

public class SearchEngine {

    private ArrayList<Entertainment> rawData;

    public void setData(ArrayList<Entertainment> rawData) {
        this.rawData = rawData;
    }

    public ArrayList<Integer> search() {
        for (Entertainment entertainment : rawData) {
            System.out.println(entertainment.getFranchise());
        }
        return new ArrayList<>();
    }

}
