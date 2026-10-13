package com.alphagnfss.etr3.data;

import com.alphagnfss.etr3.data.extensions.SegmentableExtension;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class Issue extends Entertainment { // implements SegmentableInterface {
    SegmentableExtension segment;
//
//    @Override
//    public int getSegmentNumber() {
//        return segment.getSegmentNumber();
//    }
//
//    @Override
//    public void setSegmentNumber(int segmentNumber) {
//        segment.setSegmentNumber(segmentNumber);
//    }
//
//    @Override
//    public int getPlacementId() {
//        return segment.getPlacementId();
//    }
//
//    @Override
//    public void setPlacementId(int placementId) {
//        segment.setPlacementId(placementId);
//    }
}
