package com.alphag947.controller;

import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.enumeration.EntertainmentStatus;
import com.alphag947.backend.entertainment.enumeration.EntertainmentType;
import com.alphag947.controller.uiController.MainframeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.apache.log4j.Logger;

public class ViewerController extends ParentController {
    private final Logger LOGGER = Logger.getLogger(ViewerController.class);
    protected MainframeController mfc = api.getMainframeController();

    protected String getType(EntertainmentType entertainmentType) {
        switch (entertainmentType) {
            case ANIME:
                return "Anime";
            case TVSHOW:
                return "TV Show";
            default:
                return "Null";
        }
    }

    protected String getStatus(EntertainmentStatus entertainmentStatus) {
        switch (entertainmentStatus) {
            case ONGOING:
                return "Ongoing";
            case RELEASED:
                return "Released";
            case UPCOMING:
                return "Upcoming";
            case COMPLETED:
                return "Completed";
            default:
                return "Null";
        }
    }

    private void addDataToListView(String[] tags, ListView<CenterLabel> listview) {

        listview.setPrefHeight(6 * new CenterLabel("").getPrefHeight() * 1.5);

        for (int i = 0; i < tags.length; i++) {

            // generate centerlabel
            CenterLabel cenla = new CenterLabel(tags[i]);
            cenla.getStylesheets().add(getClass().getResource("/com/alphag947/v1/css/mainframe_b2_v1.css").toExternalForm());
            cenla.getStyleClass().add("cenla");

            // print out element
            LOGGER.info(i + ": " + cenla.getText());

            /*
             * change the length of the center label based on how many
             * centerlabels in the listview
             */
            if (tags.length > 6) cenla.setPrefWidth(mfc.getViewerPlaceholder().getPrefWidth() - 42);
            else cenla.setPrefWidth(mfc.getViewerPlaceholder().getPrefWidth() - 28);

            // wrap text when text overflowing
            cenla.setWrapText(true);

            // print out the width of the centerlabel
            LOGGER.info("CenterLabel: " + cenla.getWidth());

            // add centerlabel to listview
            listview.getItems().add(cenla);
        }
    }

    public ListView<CenterLabel> generateListView(String[] array) {
        ListView<CenterLabel> listview = new ListView<>();
        listview.setCellFactory(lv -> new ListCell<CenterLabel>() {
            @Override
            protected void updateItem(CenterLabel item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    // Set the CenterLabel as the graphic for this cell
                    setGraphic(item);

                    // Ensure text color remains black on selection and non-selection
                    item.setTextFill(javafx.scene.paint.Color.BLACK);
                }
            }
        });
        VBox.setMargin(listview, new Insets(5, 5, 5, 5));
        listview.getStylesheets().add(getClass().getResource("/com/alphag947/v1/css/mainframe_b2_v1.css").toExternalForm());

        addDataToListView(array, listview);
        return listview;
    }

    protected void addStatus(VBox viewer, Entertainment entertainment) {

        if (entertainment.isSpecial())
            viewer.getChildren().add(1, new CenterLabel("Special"));

        viewer.getChildren().add(1, new CenterLabel(getStatus(entertainment.getPrimaryStatus())));
    }

    public static class CenterLabel extends Label {
        public CenterLabel(String string) {
            setText(string);
            setPrefWidth(Double.MAX_VALUE);
            setPrefHeight(33);
            setAlignment(Pos.CENTER);
            Font font = Font.font("SansSerif", FontWeight.BOLD, FontPosture.REGULAR, 14);
            setFont(font);

        }
    }
}
