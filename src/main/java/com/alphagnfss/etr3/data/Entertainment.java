package com.alphagnfss.etr3.data;

import com.alphagnfss.etr3.data.enums.EntertainmentStatus;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import com.alphagnfss.etr3.data.interfaces.EntertainmentInterface;

import java.time.LocalDate;
import java.util.Vector;

public abstract class Entertainment implements EntertainmentInterface {
    private int entertainmentId;
    private boolean isFavorite;
    private Vector<String> tags;
    private Vector<Integer> collections;
    private String title;
    private LocalDate releaseDate;
    private EntertainmentStatus status;
    private EntertainmentType type;
}
