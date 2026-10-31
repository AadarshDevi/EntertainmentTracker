package com.alphagnfss.etr3.data;

import com.alphagnfss.etr3.data.enums.EntertainmentStatus;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor // <-- Add this so Jackson can instantiate it
@AllArgsConstructor
public abstract class Entertainment {

    @JsonIgnore protected String entertainmentId;
    protected boolean isFavorite;
    @Singular protected List<String> tags;
    @Singular protected List<Integer> collections;
    @NonNull protected String title;
    protected LocalDate releaseDate;
    @NonNull protected EntertainmentStatus status;
    @NonNull protected EntertainmentType type;
    @Singular protected List<String> genres;
    protected String description;

    @JsonIgnore
    public String getHexId() {
        return null;
    }
}
