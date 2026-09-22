package com.alphagnfss.etr3.data;

import com.alphagnfss.etr3.data.extensions.StreamableExtension;
import com.alphagnfss.etr3.data.extensions.ViewableExtension;
import com.alphagnfss.etr3.data.interfaces.StreamableInterface;
import com.alphagnfss.etr3.data.interfaces.ViewableInterface;

import java.util.Vector;

public class Episode extends Entertainment implements StreamableInterface, ViewableInterface {
    StreamableExtension stream;
    ViewableExtension view;

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

    @Override
    public int getDuration() {
        return view.getDuration();
    }

    @Override
    public void setDuration(int duration) {
        view.setDuration(duration);
    }
}
