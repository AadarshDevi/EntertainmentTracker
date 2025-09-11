package com.alphag947.backend.entertainment;

import com.alphag947.backend.entertainment.enumeration.EntertainmentStatus;
import com.alphag947.backend.entertainment.enumeration.EntertainmentType;
import com.alphag947.backend.entertainment.exception.EntertainmentNotFoundException;
import com.alphag947.backend.entertainment.exception.EntertainmentStatusNotFoundException;
import lombok.Data;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Data
public class Entertainment {

//    public static final Entertainment EMPTY = new Entertainment(0, EntertainmentType.NULL, new String[0], new String[0],
//            LocalDate.now());

    private final Logger LOGGER = LogManager.getLogger(Entertainment.class);
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
//    private int secondaryStatus;
    private boolean isSpecial; // 4
    private boolean isPilot; // 5
    private boolean isFavorite; // 6
    // private boolean isReviewed; // 7
    private int id;


    private Entertainment() {
    }

    public Entertainment(
            int id, // episode id
            EntertainmentType type, // episode type

            String franchise, // Episode Title

            String title, // Episode > Null

            String[] statuses, // episode status
            String[] tags, // episode tags

            LocalDate date // episode date
    ) {
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
        } catch (NumberFormatException | EntertainmentNotFoundException e) {
            LOGGER.error(e);
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
//                    secondaryStatus++;
                    break;
                case 5: // pilot
                    isPilot = true;
//                    secondaryStatus++;
                    break;
                case 6: // favorite
                    isFavorite = true;
//                    secondaryStatus++;
                    break;
                default:
                    throw new EntertainmentStatusNotFoundException(Integer.parseInt(string));
            }
        }
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

    public String createStageName() {
        if (title == null || title.isEmpty() || title.equals("NVR")) return franchise;
        else if (franchise.contains("**")) return (franchise.replace("**", " ") + title);
        else return (franchise + ": " + title);
    }

    public String setVisualDate() {

        LocalDate today = LocalDate.now();

        if (date.isBefore(today)) primaryStatus = EntertainmentStatus.RELEASED;
        else if (date.equals(LocalDate.of(3000, 1, 1))) return "Upcoming";
        else if (date.equals(LocalDate.of(3000, 1, 2))) return "Released";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        return date.format(formatter);
    }

//    public String getDataLine() throws EntertainmentNotFoundException {
//        return type + mainDelimiter +
//                franchise + mainDelimiter +
//                title + mainDelimiter +
//                getStatusLine() + subDelimiter +
//                getTagsDataLine() + subDelimiter +
//                date;
//    }

    protected String getTagsDataLine() {
        StringBuilder tagdataline = new StringBuilder();
        for (int i = 0; i < tags.length; i++) {
            tagdataline.append(tags[i]);
            if (i != tags.length - 1) tagdataline.append(subDelimiter);
        }
        return tagdataline.toString();
    }

//    protected String getStatusLine() throws EntertainmentNotFoundException {
//        StringBuilder statusdataline = new StringBuilder();
//
//        switch (primaryStatus) {
//            case COMPLETED:
//                statusdataline.append(1 + "");
//                break;
//            case RELEASED:
//                statusdataline.append(2 + "");
//                break;
//            case UPCOMING:
//                statusdataline.append(3 + "");
//                break;
//            case ONGOING:
//                statusdataline.append(9 + "");
//                break;
//            default:
//                throw new EntertainmentStatusNotFoundException(primaryStatus);
//        }
//
////        if (secondaryStatus > 0) statusdataline.append(subDelimiter);
//
////        for (int i = 0; i < secondaryStatus; i++) {
//            if (isSpecial) statusdataline.append(4 + "");
//            if (isPilot) statusdataline.append(5 + "");
//            if (isFavorite) statusdataline.append(6 + "");
//            if (i != secondaryStatus - 1) statusdataline.append(subDelimiter);
////        }
//        // Add reviewed
//        return statusdataline.toString();
//    }

    public boolean contains(String phrase) {
        if (stageName.contains(phrase)) return true;
        for (String string : tags) if (string.contains(phrase)) return true;
        return false;
    }
}
