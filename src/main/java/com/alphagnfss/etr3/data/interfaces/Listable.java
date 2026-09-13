package com.alphagnfss.etr3.data.interfaces;

import java.util.Vector;

public interface Listable {

    int getSegmentCount();

    void setSegmentCount(int segmentCount);

    Vector<Integer> getSegmentList();

    void setSegmentList(Vector<Integer> segmentList);
}
