package com.alphagnfss.etr3.data.extensions;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Builder
@Data
public class StreamableExtension { // implements StreamableInterface {

    private boolean isSpecial;
    private boolean isPilot;
    @Singular private List<String> productionCompanies;
    @Singular private List<String> streamingServices;
//
//    public StreamableExtension() {
//        isSpecial = false;
//        isPilot = false;
//        productionCompanies = new Vector<>();
//        streamingServices = new Vector<>();
//    }
//
//    @Override
//    public boolean getIsSpecial() {
//        return isSpecial;
//    }
//
//    @Override
//    public void setIsSpecial(boolean isSpecial) {
//        this.isSpecial = isSpecial;
//    }
//
//    @Override
//    public boolean getIsPilot() {
//        return isPilot;
//    }
//
//    @Override
//    public void setIsPilot(boolean isPilot) {
//        this.isPilot = isPilot;
//    }
//
//    @Override
//    public Vector<String> getProductionCompanies() {
//        return productionCompanies;
//    }
//
//    @Override
//    public void setProductionCompanies(Vector<String> productionCompanies) {
//        this.productionCompanies = productionCompanies;
//    }
//
//    @Override
//    public Vector<String> getStreamingServices() {
//        return streamingServices;
//    }
//
//    @Override
//    public void setStreamingServices(Vector<String> streamingServices) {
//        this.streamingServices = streamingServices;
//    }
}
