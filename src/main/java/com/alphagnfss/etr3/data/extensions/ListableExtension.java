package com.alphagnfss.etr3.data.extensions;

import com.alphagnfss.etr3.data.interfaces.ListableInterface;

import java.util.Vector;

public class ListableExtension implements ListableInterface {

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
