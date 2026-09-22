package com.alphagnfss.etr3.data;

import com.alphagnfss.etr3.data.extensions.ListableExtension;
import com.alphagnfss.etr3.data.extensions.StreamableExtension;
import com.alphagnfss.etr3.data.interfaces.ListableInterface;
import com.alphagnfss.etr3.data.interfaces.StreamableInterface;

import java.util.Vector;

public class Show extends Entertainment implements StreamableInterface, ListableInterface {
    StreamableExtension stream;
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

    @Override
    public boolean getIsSpecial() {
        return stream.getIsSpecial();
    }

    @Override
    public void setIsSpecial(boolean isSpecial) {
        stream.setIsSpecial(isSpecial);
    }

    @Override
    public boolean getIsPilot() {
        return stream.getIsPilot();
    }

    @Override
    public void setIsPilot(boolean isPilot) {
        stream.setIsPilot(isPilot);
    }

    @Override
    public Vector<String> getProductionCompanies() {
        return stream.getProductionCompanies();
    }

    @Override
    public void setProductionCompanies(Vector<String> productionCompanies) {
        stream.setProductionCompanies(productionCompanies);
    }
}
