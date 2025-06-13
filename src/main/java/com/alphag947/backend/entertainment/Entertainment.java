package com.alphag947.backend.entertainment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Entertainment {
    public static final String MOVIE = "Movie";
    public static final String ANIME = "Anime";

    private String type;
    private String franchise;
    private String title;
    private String[] statuses;
    private String[] tags;
    private LocalDate date;

    protected String stageName;
    protected String visualDate;

    private int primaryStatus;
    private int secondaryStatus;

    private boolean isSpecial; // 4
    private boolean isPilot; // 5
    private boolean isFavorite; // 6
    private int id;

    public static final int COMPLETED = 1; // 1
    public static final int RELEASED = 2; // 2
    public static final int UPCOMING = 3; // 3
    public static final int ONGOING = 9; // 9

    public Entertainment(int id, String type, String franchise, String title, String[] statuses, String[] tags,
            LocalDate date) {
        this.id = id;
        this.type = type;
        this.franchise = franchise;
        this.title = title;

        this.statuses = statuses;
        this.tags = tags;
        this.date = date;

        this.isFavorite = false;
        this.isSpecial = false;
        this.isPilot = false;

        visualDate = setVisualDate();
        setStatus();
        stageName = createStageName();
    }

    public Entertainment(int id, String type, String[] statuses, String[] tags, LocalDate date) {
        this.id = id;
        this.type = type;

        this.statuses = statuses;
        this.tags = tags;
        this.date = date;

        this.isFavorite = false;
        this.isSpecial = false;
        this.isPilot = false;

        visualDate = setVisualDate();
        setStatus();
        stageName = createStageName();
    }

    private void setStatus() {

        for (String string : statuses) {
            switch (Integer.parseInt(string)) {
                case 1: // completed
                case 2: // released, ongoing
                case 3: // upcoming
                case 9: // ongoing
                    primaryStatus = Integer.parseInt(string);
                    break;
                case 4: // special
                    isSpecial = true;
                    secondaryStatus = Integer.parseInt(string);
                    break;
                case 5: // pilot
                    isPilot = true;
                    secondaryStatus = Integer.parseInt(string);
                case 6: // favorite
                    isFavorite = true;
                    secondaryStatus = Integer.parseInt(string);
                    break;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFranchise() {
        return franchise;
    }

    public String[] getStatuses() {
        return statuses;
    }

    public String[] getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getPrimaryStatus() {
        return primaryStatus;
    }

    public int getSecondaryStatus() {
        return secondaryStatus;
    }

    public String getStageName() {
        return stageName;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public void setStatuses(String[] statuses) {
        this.statuses = statuses;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPrimaryStatus(int primaryStatus) {
        this.primaryStatus = primaryStatus;
    }

    public void setSecondaryStatus(int secondaryStatus) {
        this.secondaryStatus = secondaryStatus;
    }

    public boolean equals(Entertainment inEntertainment) {

        return Arrays.equals(tags, inEntertainment.tags) &&
                inEntertainment.getType().equals(type) &&
                inEntertainment.getFranchise().equals(franchise) &&
                inEntertainment.getTitle().equals(title) &&
                inEntertainment.primaryStatus == primaryStatus &&
                inEntertainment.isSpecial == isSpecial &&
                inEntertainment.isPilot == isPilot &&
                inEntertainment.isFavorite == isFavorite &&
                inEntertainment.getDate().equals(date) &&
                inEntertainment.getStageName().equals(this.getStageName());

    }

    public boolean isDateEqual(Entertainment entertainment) {
        return this.date.isEqual(entertainment.getDate());
    }

    public boolean isFranchiseEqual(Entertainment entertainment) {
        return this.franchise.equals(entertainment.getFranchise());
    }

    public boolean isPrimaryStatusEqual(Entertainment entertainment) {
        return this.primaryStatus == entertainment.getPrimaryStatus();
    }

    public boolean isTitleEqual(Entertainment entertainment) {
        return this.title.equals(entertainment.getTitle());
    }

    public boolean isTypeEqual(Entertainment entertainment) {
        return this.type.equals(entertainment.getType());
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public boolean isPilot() {
        return isPilot;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public String createStageName() {
        String sn = "";
        if (title == null || title.isEmpty() || title.equals("NVR")) {
            sn = franchise;
        } else if (franchise.contains("**")) {
            sn = (franchise.replace("**", " ") + title);
        } else {
            sn = (franchise + ": " + title);
        }

        return sn;
    }

    public String setVisualDate() {

        LocalDate today = LocalDate.now();

        if (date.isBefore(today)) {
            primaryStatus = 2;
        }

        if (date.equals(LocalDate.of(3000, 01, 01))) {
            return "Upcoming";
        } else if (date.equals(LocalDate.of(3000, 01, 02))) {
            return "Released";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

    public String getVisualDate() {
        return visualDate;
    }
}
