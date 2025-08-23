package com.alphag947.controller.entertainmentViewer.dataViewer;

import com.alphag947.App;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.Movie;
import com.alphag947.controller.ViewerController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import org.apache.log4j.Logger;

import java.time.LocalDate;

public class MovieViewerController extends ViewerController {
    private final Logger LOGGER = Logger.getLogger(MovieViewerController.class);
    @FXML
    private VBox viewer;
    @FXML
    private Label e_franchise;
    @FXML
    private Label e_title;
    @FXML
    private Label e_duration;

    public void view(Entertainment entertainment) {

        Movie movie = (Movie) entertainment;

        if (App.DEBUG) cl.dbg("MovieViewer: " + entertainment.getStageName());

        addStatus(movie);

        e_franchise.setText(movie.getFranchise().replace("**", ""));

        if (movie.getTitle() == null || movie.getTitle().isBlank() || movie.getTitle().isEmpty()) {
            e_title.setManaged(false);
            e_title.setVisible(false);
            viewer.requestLayout();
        } else {
            e_title.setManaged(true);
            e_title.setVisible(true);
            e_title.requestLayout();
            e_title.setText(entertainment.getTitle());
        }

        if (movie.getDuration() <= 0) {
            e_duration.setText("Duration: Unknown");
        } else e_duration.setText("Duration: " + movie.getDuration() + " min");

        if (movie.getDate().equals(LocalDate.of(3000, 1, 1))) {
            e_duration.setText("Release Date: Unknown");
        } else e_duration.setText("Release Date: " + movie.getVisualDate());

        /*
         * generate listviews for Production Companies and Tags
         */
        ListView<CenterLabel> productionCompaniiesListView = generateListView(movie.getAnimationCompanies());
        ListView<CenterLabel> tags = generateListView(movie.getTags());

        /*
         * add listviews to viewer
         */
        viewer.getChildren().add(productionCompaniiesListView);
        viewer.getChildren().add(tags);

        /*
         * show that the viewer has the right width
         */
        LOGGER.info("Viewer: " + viewer.getWidth());
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

    private void addStatus(Movie movie) {
        if (movie.isPilot()) viewer.getChildren().add(1, new CenterLabel("Pilot"));
        if (movie.isSpecial()) viewer.getChildren().add(1, new CenterLabel("Special"));
        viewer.getChildren().add(1, new CenterLabel(getStatus(movie.getPrimaryStatus())));
    }
}
