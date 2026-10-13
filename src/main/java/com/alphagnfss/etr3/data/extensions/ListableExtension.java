package com.alphagnfss.etr3.data.extensions;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class ListableExtension { // implements ListableInterface {

    private int segmentCount;
    @Singular private List<Integer> segments;
//
//    public ListableExtension() {
//        this(-1, null);
//    }
//
//    public ListableExtension(int segmentCount, Vector<Integer> segmentList) {
//        this.segmentCount = segmentCount;
//        this.segmentList = segmentList;
//    }
//
//    @Override
//    public int getSegmentCount() {
//        return segmentCount;
//    }
//
//    @Override
//    public void setSegmentCount(int segmentCount) {
//        this.segmentCount = segmentCount;
//    }
//
//    @Override
//    public Vector<Integer> getSegmentList() {
//        return segmentList;
//    }
//
//    @Override
//    public void setSegmentList(Vector<Integer> segmentList) {
//        this.segmentList = segmentList;
//    }
}
