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
    private Vector<String> genre;
    private String description;

    @Override
    public boolean getIsFavorite() {
        return isFavorite;
    }

    @Override
    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public int getEntertainmentId() {
        return entertainmentId;
    }

    @Override
    public void setEntertainmentId(int id) {
        this.entertainmentId = id;
    }

    @Override
    public Vector<Integer> getCollections() {
        return collections;
    }

    @Override
    public void setCollections(Vector<Integer> collections) {
        this.collections = collections;
    }

    @Override
    public EntertainmentType getType() {
        return type;
    }

    @Override
    public void setType(EntertainmentType type) {
        this.type = type;
    }

    @Override
    public EntertainmentStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(EntertainmentStatus status) {
        this.status = status;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public Vector<String> getTags() {
        return tags;
    }

    @Override
    public void setTags(Vector<String> tags) {
        this.tags = tags;
    }

    public String getMessage() {
        return "Entertainment says \"Hello\".";
    }

    @Override
    public Vector<String> getGenre() {
        return genre;
    }

    @Override
    public void setGenre(Vector<String> genre) {
        this.genre = genre;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
