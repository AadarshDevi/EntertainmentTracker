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
}
