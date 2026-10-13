package com.alphagnfss.etr3.data;

import com.alphagnfss.etr3.data.enums.EntertainmentStatus;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import com.alphagnfss.etr3.data.extensions.ListableExtension;
import com.alphagnfss.etr3.data.extensions.StreamableExtension;
import com.alphagnfss.etr3.data.interfaces.ListableInterface;
import com.alphagnfss.etr3.data.interfaces.StreamableInterface;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Vector;

@Builder
public class Show extends Entertainment implements StreamableInterface, ListableInterface {
    StreamableExtension stream;
    ListableExtension list;

//    public Show(EntertainmentType type, String title) {
//        super();
//        stream = new StreamableExtension();
//        list = new ListableExtension();
//        this.type = type;
//        this.title = title;
//    }
//
//    public Show() {
//        this(
//                -1, false,
//                null, null,
//                null, null,
//                null, null,
//                null, null,
//                null, null
//        );
//    }

    public Show(
            int entertainmentId,
            boolean isFavorite,
            Vector<String> tags,
            Vector<Integer> collections,
            String title,
            LocalDate releaseDate,
            EntertainmentStatus status,
            EntertainmentType type,
            Vector<String> genre,
            String description,
            StreamableExtension stream,
            ListableExtension list
    ) {
        super(
                entertainmentId, isFavorite,
                tags, collections,
                title, releaseDate,
                status, type,
                genre, description
        );
        this.stream = stream;
        this.list = list;
    }


    @Override
    public int getSegmentCount() {
        return list.getSegmentCount();
    }

    @Override
    public void setSegmentCount(int segmentCount) {
        list.setSegmentCount(segmentCount);
    }

    @Override
    public Vector<Integer> getSegmentList() {
        return list.getSegmentList();
    }

    @Override
    public void setSegmentList(Vector<Integer> segmentList) {
        list.setSegmentList(segmentList);
    }

    @Override
    public boolean getIsSpecial() {
        return stream.getIsSpecial();
    }

    @Override
    public void setIsSpecial(boolean isSpecial) {
        stream.setIsSpecial(isSpecial);
    }

    @Override
    public boolean getIsPilot() {
        return stream.getIsPilot();
    }

    @Override
    public void setIsPilot(boolean isPilot) {
        stream.setIsPilot(isPilot);
    }

    @Override
    public Vector<String> getProductionCompanies() {
        return stream.getProductionCompanies();
    }

    @Override
    public void setProductionCompanies(Vector<String> productionCompanies) {
        stream.setProductionCompanies(productionCompanies);
    }

    @Override
    public Vector<String> getStreamingServices() {
        return null;
    }

    @Override
    public void setStreamingServices(Vector<String> streamingServices) {

    }
}
