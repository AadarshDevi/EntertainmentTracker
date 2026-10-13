package com.alphagnfss.etr3.data.extensions;

import com.alphagnfss.etr3.data.interfaces.StreamableInterface;

import java.util.Vector;

public class StreamableExtension implements StreamableInterface {

    private boolean isSpecial;
    private boolean isPilot;
    private Vector<String> productionCompanies;
    private Vector<String> streamingServices; // Streaming Services

    @Override
    public boolean getIsSpecial() {
        return isSpecial;
    }

    @Override
    public void setIsSpecial(boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    @Override
    public boolean getIsPilot() {
        return isPilot;
    }

    @Override
    public void setIsPilot(boolean isPilot) {
        this.isPilot = isPilot;
    }

    @Override
    public Vector<String> getProductionCompanies() {
        return productionCompanies;
    }

    @Override
    public void setProductionCompanies(Vector<String> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    @Override
    public Vector<String> getStreamingServices() {
        return streamingServices;
    }

    @Override
    public void setStreamingServices(Vector<String> streamingServices) {
        this.streamingServices = streamingServices;
    }
}
