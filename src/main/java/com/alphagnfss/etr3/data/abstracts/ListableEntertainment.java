package com.alphagnfss.etr3.data.abstracts;

import com.alphagnfss.etr3.data.interfaces.Listable;

import java.util.Vector;

public abstract class ListableEntertainment implements Listable {
    private int segmentCount;
    private Vector<Integer> segmentList;

    @Override
    public int getSegmentCount() {
        return 0;
    }

    @Override
    public void setSegmentCount(int segmentCount) {

    }

    @Override
    public Vector<Integer> getSegmentList() {
        return null;
    }

    @Override
    public void setSegmentList(Vector<Integer> segmentList) {

    }
}
