package com.alphagnfss.etr3.data.interfaces;


import com.alphagnfss.etr3.data.enums.EntertainmentStatus;
import com.alphagnfss.etr3.data.enums.EntertainmentType;

import java.time.LocalDate;
import java.util.Vector;

public interface EntertainmentInterface {

    /**
     * Gets the boolean is Favorite. Does the user like this entertainment?
     *
     * @return is favorite
     */
    boolean getIsFavorite();

    void setIsFavorite(boolean isFavorite);

    int getEntertainmentId();

    void setEntertainmentId(int id);

    Vector<Integer> getCollections();

    void setCollections(Vector<Integer> collections);

    EntertainmentType getType();

    void setType(EntertainmentType type);

    EntertainmentStatus getStatus();

    void setStatus(EntertainmentStatus status);

    String getTitle();

    void setTitle(String title);

    LocalDate getReleaseDate();

    void setReleaseDate(LocalDate releaseDate);

    Vector<String> getTags();

    void setTags(Vector<String> tags);

    Vector<String> getGenre();

    void setGenre(Vector<String> genre);

    String getDescription();

    void setDescription(String description);
}
