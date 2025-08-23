package com.alphag947.controller.entertainmentViewer.dataViewer;

import com.alphag947.backend.entertainment.Episode;
import com.alphag947.backend.entertainment.Show;
import com.alphag947.controller.ViewerController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import org.apache.log4j.Logger;

public class EpisodeViewerController extends ViewerController {

    private final Logger LOGGER = Logger.getLogger(EpisodeViewerController.class);

    @FXML
    private VBox viewer;
    @FXML
    private Label show_franchise;
    @FXML
    private Label season_title;
    @FXML
    private Label season_num;
    @FXML
    private Label release_date;
    @FXML
    private Label ep_num;
    @FXML
    private Label ep_name;
    @FXML
    private Label runtime;

    public void view(Show show, Episode episode) {
        LOGGER.info(show.getStageName() + // show stagename
                " S" + show.getSeasonNum() + // season num
                " E" + episode.getEpisodeNum() + // episode num
                ": " + episode.getEpisodeTitle() // episode name
        );

        if (show.getTitle() != null) {
            if (show.getStageName().length() <= 50) {
                LOGGER.info("has title : length <= 50");
                season_title.setManaged(false);
                season_title.setVisible(false);
                viewer.requestLayout();
                show_franchise.setText(show.getStageName());
            } else {
                LOGGER.info("has title : length > 50");
                show_franchise.setText(show.getFranchise());
                season_title.setText(show.getTitle());
            }
        } else {
            show_franchise.setText(show.getFranchise());
        }

        season_num.setText(show.getVisualSeason());
        release_date.setText(episode.getVisualDate());
        ep_name.setText(episode.getEpisodeTitle());
        ep_num.setText("Episode " + episode.getEpisodeNum());
        viewer.getChildren().add(1, new CenterLabel(getStatus(episode.getPrimaryStatus())));

        runtime.setText(String.format("Duration: %d min", episode.getDuration()));

        // TODO: add tags later
        ListView<CenterLabel> listview = generateListView(episode.getTags());
        viewer.getChildren().add(listview);
    }
}
