package com.alphagnfss.etr3.data.extensions;

import com.alphagnfss.etr3.data.interfaces.ListableInterface;

import java.util.Vector;

public class ListableExtension implements ListableInterface {

    private final int segmentCount;
    private final Vector<Integer> segmentList;

    public ListableExtension() {
        this(-1, null);
    }

    public ListableExtension(int segmentCount, Vector<Integer> segmentList) {
        this.segmentCount = segmentCount;
        this.segmentList = segmentList;
    }

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
