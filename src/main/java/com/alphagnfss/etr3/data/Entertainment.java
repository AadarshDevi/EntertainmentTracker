package com.alphagnfss.etr3.data;

import com.alphagnfss.etr3.data.enums.EntertainmentStatus;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import com.alphagnfss.etr3.data.interfaces.EntertainmentInterface;

import java.time.LocalDate;
import java.util.Vector;

public abstract class Entertainment implements EntertainmentInterface {
    protected int entertainmentId;
    protected boolean isFavorite;
    protected Vector<String> tags;
    protected Vector<Integer> collections;
    protected String title;
    protected LocalDate releaseDate;
    protected EntertainmentStatus status;
    protected EntertainmentType type;
    protected Vector<String> genre;
    protected String description;

//    public Entertainment(
//            int entertainmentId,
//            boolean isFavorite,
//            Vector<String> tags,
//            Vector<Integer> collections,
//            String title,
//            LocalDate releaseDate,
//            EntertainmentStatus status,
//            EntertainmentType type,
//            Vector<String> genre,
//            String description
//    ) {
//        this.entertainmentId = entertainmentId;
//        this.tags = tags;
//        this.collections = collections;
//        this.isFavorite = isFavorite;
//        this.title = title;
//        this.releaseDate = releaseDate;
//        this.status = status;
//        this.type = type;
//        this.genre = genre;
//        this.description = description;
//    }
//
//    public Entertainment() {
//        this(
//                -1, false,
//                null, null,
//                null, null,
//                null, null,
//                null, null
//        );
//    }

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

//    protected void setDefault() {
//        this.entertainmentId = -1;
//        this.isFavorite = false;
//        this.tags = new Vector<>();
//        this.collections = new Vector<>();
//        this.title = null;
//        this.releaseDate = LocalDate.MIN;
//        this.status = null;
//        this.type = null;
//        this.genre = new Vector<>();
//        this.description = null;
//    }
}
