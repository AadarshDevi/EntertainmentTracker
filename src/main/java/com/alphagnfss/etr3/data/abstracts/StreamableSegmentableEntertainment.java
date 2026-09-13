package com.alphagnfss.etr3.data.abstracts;

import com.alphagnfss.etr3.data.interfaces.Segmentable;

public abstract class StreamableSegmentableEntertainment extends ViewableStreamableEntertainment implements Segmentable {
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

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public void setDuration(int duration) {

    }
}
