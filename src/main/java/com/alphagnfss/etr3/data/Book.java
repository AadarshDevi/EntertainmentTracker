package com.alphagnfss.etr3.data;

import com.alphagnfss.etr3.data.extensions.ListableExtension;
import com.alphagnfss.etr3.data.interfaces.ListableInterface;

import java.util.Vector;

public class Book extends Entertainment implements ListableInterface {
    ListableExtension list;

    @Override
    public int getSegmentCount() {
        return list.getSegmentCount();
    }

    @Override
    public void setSegmentCount(int segmentCount) {
        list.setSegmentCount(segmentCount);
    }

    @Override
    public Vector<Integer> getSegmentList() {
        return list.getSegmentList();
    }

    @Override
    public void setSegmentList(Vector<Integer> segmentList) {
        list.setSegmentList(segmentList);
    }
}
