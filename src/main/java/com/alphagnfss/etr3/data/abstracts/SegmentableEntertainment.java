package com.alphagnfss.etr3.data.abstracts;

import com.alphagnfss.etr3.data.Entertainment;
import com.alphagnfss.etr3.data.interfaces.Segmentable;

public abstract class SegmentableEntertainment extends Entertainment implements Segmentable {
    private int segmentNum;
    private int placementId;
}
