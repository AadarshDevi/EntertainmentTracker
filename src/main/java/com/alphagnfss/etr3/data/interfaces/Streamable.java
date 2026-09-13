package com.alphagnfss.etr3.data.interfaces;

import java.util.Vector;

public interface Streamable {
    
    boolean getIsSpecial();

    void setIsSpecial(boolean isSpecial);

    boolean getIsPilot();

    void setIsPilot(boolean isPilot);

    Vector<String> getProductionCompanies();

    void setProductionCompanies(Vector<String> productionCompanies);
}
