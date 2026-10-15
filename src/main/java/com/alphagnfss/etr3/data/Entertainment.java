package com.alphagnfss.etr3.data;

import com.alphagnfss.etr3.data.enums.EntertainmentStatus;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@Setter
@Getter
public abstract class Entertainment {

    @JsonProperty("_id")
    @NonNull
    protected Integer entertainmentId;
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
        // # - (letter) hex num symbol
        // % - format specifier
        // 04 - Tells String to pad the number with 0s to get a length of 4 chars
        // X - num base - Hexadecimal
        return String.format("#%04X", (entertainmentId + 1));
    }
}
