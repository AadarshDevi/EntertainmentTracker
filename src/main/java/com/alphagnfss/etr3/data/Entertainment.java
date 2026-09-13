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

    @Override
    public boolean getIsFavorite() {
        return false;
    }

    @Override
    public void setIsFavorite(boolean isFavorite) {

    }

    @Override
    public int getEntertainmentId() {
        return 0;
    }

    @Override
    public void setEntertainmentId(int id) {

    }

    @Override
    public Vector<Integer> getCollections() {
        return null;
    }

    @Override
    public void setCollections(Vector<Integer> collections) {

    }

    @Override
    public EntertainmentType getType() {
        return null;
    }

    @Override
    public void setType(EntertainmentType type) {

    }

    @Override
    public EntertainmentStatus getStatus() {
        return null;
    }

    @Override
    public void setStatus(EntertainmentStatus status) {

    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public LocalDate getReleaseDate() {
        return null;
    }

    @Override
    public void setReleaseDate(LocalDate releaseDate) {

    }

    @Override
    public Vector<String> getTags() {
        return null;
    }

    @Override
    public void setTags(Vector<String> tags) {

    }

    public String getMessage() {
        return "Entertainment says \"Hello\".";
    }

}
