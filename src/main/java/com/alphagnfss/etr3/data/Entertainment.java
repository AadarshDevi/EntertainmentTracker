package com.alphagnfss.etr3.data;

import com.alphagnfss.etr3.data.enums.EntertainmentStatus;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@Setter
@Getter
public abstract class Entertainment {
    protected int entertainmentId;
    protected boolean isFavorite;
    @Singular protected List<String> tags;
    @Singular protected List<Integer> collections;
    protected String title;
    protected LocalDate releaseDate;
    protected EntertainmentStatus status;
    protected EntertainmentType type;
    @Singular protected List<String> genres;
    protected String description;
}
