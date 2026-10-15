package com.alphagnfss.etr3.data.extensions;

import com.alphagnfss.etr3.data.interfaces.SegmentableInterface;

public class SegmentableExtension implements SegmentableInterface {

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
