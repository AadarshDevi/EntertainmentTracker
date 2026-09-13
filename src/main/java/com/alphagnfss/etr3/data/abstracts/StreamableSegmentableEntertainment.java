package com.alphagnfss.etr3.data.abstracts;

import com.alphagnfss.etr3.data.interfaces.Segmentable;

public abstract class StreamableSegmentableEntertainment extends ViewableStreamableEntertainment implements Segmentable {
    private int segmentNum;
    private int placementId;
}
