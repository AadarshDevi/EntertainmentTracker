package com.alphagnfss.etr3.data.abstracts.streamable;

import com.alphagnfss.etr3.data.Entertainment;
import com.alphagnfss.etr3.data.interfaces.Streamable;

import java.util.Vector;

public abstract class StreamableEntertainment extends Entertainment implements Streamable {
    private boolean isSpecial;
    private boolean isPilot;
    private Vector<String> productionCompanies;

    @Override
    public boolean getIsSpecial() {
        return false;
    }

    @Override
    public void setIsSpecial(boolean isSpecial) {

    }

    @Override
    public boolean getIsPilot() {
        return false;
    }

    @Override
    public void setIsPilot(boolean isPilot) {

    }

    @Override
    public Vector<String> getProductionCompanies() {
        return null;
    }

    @Override
    public void setProductionCompanies(Vector<String> productionCompanies) {

    }
}
