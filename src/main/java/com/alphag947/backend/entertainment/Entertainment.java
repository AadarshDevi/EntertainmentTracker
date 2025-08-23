package com.alphag947.backend.entertainment;

import com.alphag947.backend.entertainment.enumeration.EntertainmentStatus;
import com.alphag947.backend.entertainment.enumeration.EntertainmentType;
import com.alphag947.backend.entertainment.exception.EntertainmentNotFoundException;
import com.alphag947.backend.entertainment.exception.EntertainmentStatusNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Entertainment {

    public static final Entertainment EMPTY = new Entertainment(0, EntertainmentType.NULL, new String[0], new String[0],
            LocalDate.now());
    public String mainDelimiter = "<##>";
    public String subDelimiter = "<<>>";
    protected String stageName;
    protected String visualDate;
    private EntertainmentType type;
    private String franchise;
    private String title;
    private String[] statuses;
    private String[] tags;
    private LocalDate date;

    // public static final int COMPLETED = 1; // 1
    // public static final int RELEASED = 2; // 2
    // public static final int UPCOMING = 3; // 3
    // public static final int ONGOING = 9; // 9
    private EntertainmentStatus primaryStatus;
    private int secondaryStatus;
    private boolean isSpecial; // 4
    private boolean isPilot; // 5
    private boolean isFavorite; // 6
    // private boolean isReviewed; // 7
    private int id;

    public Entertainment(int id,
                         EntertainmentType type, String franchise, String title, String[] statuses,
                         String[] tags,
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
        try {
            setStatus();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (EntertainmentNotFoundException e) {
            e.printStackTrace();
        }
        stageName = createStageName();
    }

    public Entertainment(int id, EntertainmentType type, String[] statuses, String[] tags, LocalDate date) {
        this.id = id;
        this.type = type;

        this.statuses = statuses;
        this.tags = tags;
        this.date = date;

        this.isFavorite = false;
        this.isSpecial = false;
        this.isPilot = false;

        visualDate = setVisualDate();
        try {
            setStatus();
        } catch (NumberFormatException | EntertainmentNotFoundException e) {
            e.printStackTrace();
        }
        stageName = createStageName();
    }


    private void setStatus() throws NumberFormatException, EntertainmentNotFoundException {

        for (String string : statuses) {
            switch (Integer.parseInt(string)) {
                case 1: // completed
                    primaryStatus = EntertainmentStatus.COMPLETED;
                    break;
                case 2: // released
                    primaryStatus = EntertainmentStatus.RELEASED;
                    break;
                case 3: // upcoming
                    primaryStatus = EntertainmentStatus.UPCOMING;
                    break;
                case 9: // ongoing
                    primaryStatus = EntertainmentStatus.ONGOING;
                    break;
                case 4: // special
                    isSpecial = true;
                    secondaryStatus++;
                    break;
                case 5: // pilot
                    isPilot = true;
                    secondaryStatus++;
                    break;
                case 6: // favorite
                    isFavorite = true;
                    secondaryStatus++;
                    break;
                default:
                    throw new EntertainmentStatusNotFoundException(Integer.parseInt(string));
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

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String[] getStatuses() {
        return statuses;
    }

    public void setStatuses(String[] statuses) {
        this.statuses = statuses;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EntertainmentType getType() {
        return type;
    }

    public void setType(EntertainmentType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EntertainmentStatus getPrimaryStatus() {
        return primaryStatus;
    }

    public void setPrimaryStatus(EntertainmentStatus primaryStatus) {
        this.primaryStatus = primaryStatus;
    }

    public String getStageName() {
        return stageName;
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
        if (title == null || title.isEmpty() || title.equals("NVR")) return franchise;
        else if (franchise.contains("**")) return (franchise.replace("**", " ") + title);
        else return (franchise + ": " + title);
    }

    public String setVisualDate() {

        LocalDate today = LocalDate.now();

        if (date.isBefore(today)) primaryStatus = EntertainmentStatus.RELEASED;
        else if (date.equals(LocalDate.of(3000, 01, 01))) return "Upcoming";
        else if (date.equals(LocalDate.of(3000, 01, 02))) return "Released";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

    public String getVisualDate() {
        return visualDate;
    }

    public String getDataLine() throws EntertainmentNotFoundException {
        return type + mainDelimiter +
                franchise + mainDelimiter +
                title + mainDelimiter +
                getStatusLine() + subDelimiter +
                getTagsDataLine() + subDelimiter +
                date;
    }

    protected String getTagsDataLine() {
        String tagdataline = "";
        for (int i = 0; i < tags.length; i++) {
            tagdataline += tags[i];
            if (i != tags.length - 1) tagdataline += subDelimiter;
        }
        return tagdataline;
    }

    protected String getStatusLine() throws EntertainmentNotFoundException {
        String statusdataline = "";

        switch (primaryStatus) {
            case COMPLETED:
                statusdataline += 1 + "";
                break;
            case RELEASED:
                statusdataline += 2 + "";
                break;
            case UPCOMING:
                statusdataline += 3 + "";
                break;
            case ONGOING:
                statusdataline += 9 + "";
                break;
            default:
                throw new EntertainmentStatusNotFoundException(primaryStatus);
        }

        if (secondaryStatus > 0) statusdataline += subDelimiter;

        for (int i = 0; i < secondaryStatus; i++) {
            if (isSpecial) statusdataline += 4 + "";
            if (isPilot) statusdataline += 5 + "";
            if (isFavorite) statusdataline += 6 + "";
            if (i != secondaryStatus - 1) statusdataline += subDelimiter;
        }
        // Add reviewed
        return statusdataline;
    }

    public boolean contains(String phrase) {
        if (stageName.contains(phrase)) return true;
        for (String string : tags) if (string.contains(phrase)) return true;
        return false;
    }
}
