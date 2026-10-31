package com.alphagnfss.etr3.database;

import com.alphagnfss.etr3.data.enums.EntertainmentStatus;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import lombok.Builder;
import lombok.Singular;

import java.util.List;
import java.util.Map;

@Builder
public record SearchQuery(
        String title,
        Boolean favorite,
        @Singular List<String> tags,
        @Singular List<String> collections,
        @Singular List<String> genres,
        EntertainmentType type,
        EntertainmentStatus status,
        @Singular Map<String, String> extras
) {
}
