package com.alphag947.controller.entertainmentViewer.dataViewer;

import com.alphag947.App;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.Show;
import com.alphag947.controller.ViewerController;
import com.alphag947.v2.controller.custom.CaseChanger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import org.apache.log4j.Logger;

import java.time.LocalDate;

public class ShowViewerController extends ViewerController {

    private final Logger LOGGER = Logger.getLogger(ShowViewerController.class);

    @FXML
    private VBox viewer;
    @FXML
    private Label show_type;
    @FXML
    private Label show_franchise;
    @FXML
    private Label season_name;
    @FXML
    private Label season_num;
    @FXML
    private Label episode_count;
    @FXML
    private Label release_date;

    public void view(Entertainment entertainment) {

        /*
         * TODO: insert tags and statuses
         */

        Show show = (Show) entertainment;
        if (App.DEBUG) cl.dbg("ShowViewer: " + show.getStageName());
        addStatus(viewer, show);
        LOGGER.info(CaseChanger.toCamelCase(show.getType().toString()));
        show_type.setText(getType(show.getType()));
        show_franchise.setText(show.getFranchise().replace("**", ""));

        if (show.getTitle() == null || show.getTitle().isBlank() || show.getTitle().isEmpty()) {
            season_name.setManaged(false);
            season_name.setVisible(false);
            viewer.requestLayout();
        } else {
            season_name.setManaged(true);
            season_name.setVisible(true);
            season_name.requestLayout();
            season_name.setText(entertainment.getTitle());
        }

        season_num.setText(show.getVisualSeason());
        episode_count.setText(show.getVisualEpisodeCount());

        ListView<CenterLabel> tags = generateListView(show.getTags());
        viewer.getChildren().add(tags);

        if (show.getDate().equals(LocalDate.of(3000, 1, 1))) {
            release_date.setText("Release Date: Unknown");
        } else
            release_date.setText("Release Date: " + show.getVisualDate());


//        ListView<CenterLabel> listView = new ListView<>();
//        viewer.getChildren().add(listView);
//        cl.log(this, "Viewer: " + viewer.getWidth());
//        for (int i = 0; i < show.getTags().length; i++) {
//            CenterLabel cenla = new CenterLabel(show.getTags()[i]);
//            cl.log(this, "Production Company " + i + ": " + cenla.getText());
//            if (show.getTags().length > 8)
//                cenla.setPrefWidth(mfc.getViewerPlaceholder().getPrefWidth() - 25);
//            else cenla.setPrefWidth(mfc.getViewerPlaceholder().getPrefWidth() - 17);
//            cenla.setWrapText(true);
//            cl.log(this, "CenterLabel: " + cenla.getWidth());
//            listView.getItems().add(cenla);
//        }
    }



}
