package com.alphagnfss.etr3.data.abstracts.segmentable;

import com.alphagnfss.etr3.data.Entertainment;
import com.alphagnfss.etr3.data.interfaces.Segmentable;

public abstract class SegmentableEntertainment extends Entertainment implements Segmentable {
    private int segmentNum;
    private int placementId;

    @Override
    public int getSegmentNumber() {
        return 0;
    }

    @Override
    public void setSegmentNumber(int segmentNumber) {

    }

    @Override
    public int getPlacementId() {
        return 0;
    }

    @Override
    public void setPlacementId(int placementId) {

    }
}
