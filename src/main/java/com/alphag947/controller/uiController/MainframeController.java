package com.alphag947.controller.uiController;

import com.alphag947.App;
import com.alphag947.api.Api;
import com.alphag947.api.ApiFactory;
import com.alphag947.api.SortType;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.Episode;
import com.alphag947.backend.entertainment.Movie;
import com.alphag947.backend.entertainment.Show;
import com.alphag947.backend.entertainment.exception.EntertainmentIdNotFoundException;
import com.alphag947.backend.entertainment.exception.EntertainmentTypeNotFoundException;
import com.alphag947.backend.fxmlLoading.FXMLFactory;
import com.alphag947.backend.fxmlLoading.FXMLPackage;
import com.alphag947.controller.ParentController;
import com.alphag947.controller.entertainmentViewer.dataModule.MovieModuleController;
import com.alphag947.controller.entertainmentViewer.dataModule.ShowModuleController;
import com.alphag947.controller.entertainmentViewer.dataViewer.EpisodeViewerController;
import com.alphag947.controller.entertainmentViewer.dataViewer.MovieViewerController;
import com.alphag947.controller.entertainmentViewer.dataViewer.ShowViewerController;
import com.alphag947.v2.cli.CommandLineInterface;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.fxmisc.richtext.StyleClassedTextArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.*;

public class MainframeController extends ParentController {

    private final BorderPane noEpisodesPane = new BorderPane();
    private final Label noEpisodesLabel = new Label("No Entertainment. Create Movie or Shows to view here");
    private final Logger LOGGER = Logger.getLogger(MainframeController.class);
    private Api api;
    private int listId = 0;
    private boolean viewerEnabled;
    private boolean hasSameData;
    private String currentStageName = "";
    private Entertainment currentEntertainment;
    private CommandLineInterface cli = new CommandLineInterface();
    private StyleClassedTextArea scta;
    private TextArea ta;

    /*
     * Menu Bar
     *
     * mi_app_close:
     * mi_test: tests and event like mouse click keyboard click and other.
     *
     * Sorting Modules:
     * sortByName: sorts data in ListView by stage name
     * sortById: sorts data by their ids
     * sortByTypeTheName: sorts data by their type and then by their name.
     *
     * Window Size:
     * mi_75_percent: makes the window width and height 75% of width and height
     * mi_80_percent: makes the window width and height 80% of width and height
     * mi_fullscreen: makes app fullscreen
     *
     *
     *
     */
    @FXML
    private MenuBar menubar;
    @FXML
    private MenuItem mi_app_close; // closes the app
    @FXML
    @SuppressWarnings("unused")
    private MenuItem mi_test;
    @SuppressWarnings("unused")
    @FXML
    private MenuItem mi_sortByName;
    @FXML
    private MenuItem mi_sortById;
    @FXML
    private MenuItem mi_sortByTypeTheName;

    @FXML
    private MenuItem mi_viewer;
    @FXML
    private Menu m_monitors;

    @FXML
    private MenuItem mi_75_percent;
    @FXML
    private MenuItem mi_80_percent;
    @FXML
    private MenuItem mi_90_percent;
    @FXML
    private MenuItem mi_100_percent;
    @FXML
    private MenuItem mi_fullscreen;

    @FXML
    private TextField search_bar_textfield;
    @FXML
    private Button search_bar_search_button;

    @FXML
    private SplitPane splitPane;

    @FXML
    private ListView<BorderPane> list_view;
    @FXML
    private AnchorPane info_viewer_placeholder;
    @FXML
    private BorderPane homebase;
    @FXML
    private HBox searchbar;


    @FXML
    public void initialize() {

        searchbar.getChildren().remove(search_bar_textfield);

        // TODO: Create padding for textarea
        ta = new TextArea();
        ta.setEditable(false);


        /*
         * this is creating a commandline text so that
         */
        scta = new StyleClassedTextArea();
        scta.setWrapText(false);

        scta.setPrefHeight(35);
        scta.setMinHeight(35);

        HBox.setHgrow(scta, Priority.ALWAYS);

        scta.setPrefWidth(Region.USE_COMPUTED_SIZE);
        scta.setMaxWidth(Double.MAX_VALUE);

        HBox.setMargin(scta, new Insets(0, 0, 5, 0));

        scta.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/alphag947/v1/css/texthighlighting.css")).toExternalForm());
        scta.getStyleClass().add("style-classed-text-area");


        scta.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {

                String text = scta.getText();
                search_bar_textfield.setText(text);
                LOGGER.info(text);
                ta.setText(ta.getText() + "\n" + text);
                scta.clear();
                e.consume();

                switch (text.strip()) {
                    case "@cli enter=true" -> cli.setInCLI(true);
                    case "@cli exit=true" -> cli.setInCLI(false);
                    case "@cli enterview=true" -> {
                        cli.setInCLI(true);
                        setCliView();
                    }
                    case "@cli exitview=true" -> {
                        cli.setInCLI(false);
                        resetMainUI();
                    }
                    default -> {
                        if (cli.isInCLI()) cli.sendCommand(text);
                    }
                }
            }
        });

        scta.textProperty().addListener((obs, oldtext, newtext) -> {
            scta.setStyleSpans(0, computeHighlighting(newtext));
        });
        scta.requestFocus();
        searchbar.getChildren().add(scta);

        mi_75_percent.setOnAction(e -> resizeApp(0.75, 0.75, 0.3));
        mi_80_percent.setOnAction(e -> resizeApp(0.8, 0.8, 0.3));
        mi_90_percent.setOnAction(e -> resizeApp(0.9, 0.9, 0.25));
        mi_100_percent.setOnAction(e -> resizeApp(1, 1, 0.2));

        mi_fullscreen.setOnAction(e -> {
            api.getSceneManager().getStage().setFullScreen(true);
            cl.log(this, "App: width  = " + api.getSceneManager().getStage().getWidth() + ", height = " + api.getSceneManager().getStage().getHeight());
            setViewerWidth(0.2);
            try {
                hasSameData = false;
                viewEntertainment(currentEntertainment);
            } catch (EntertainmentTypeNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        api = ApiFactory.getApi();
//        info_viewer_placeholder.setBackground(new Background(new BackgroundFill(Paint.valueOf("BLUE"), CornerRadii.EMPTY, Insets.EMPTY)));

        viewerEnabled = true;
        hasSameData = true;
        mi_viewer.setText("Open Viewer");

        splitPane.requestLayout();

        AnchorPane.setTopAnchor(info_viewer_placeholder, 0.0);
        AnchorPane.setBottomAnchor(info_viewer_placeholder, 0.0);
        AnchorPane.setLeftAnchor(info_viewer_placeholder, 0.0);
        AnchorPane.setRightAnchor(info_viewer_placeholder, 0.0);

        setViewerWidth(0.24);
        api.getSceneManager().setStageSize(0.9, 0.9);
    }


    private StyleSpans<Collection<String>> computeHighlighting(String text) {
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        if (text.startsWith("@cli")) cli.setInCLI(true);

        int lastIndex = 0;
        String[] tokens = text.split(" ");

        for (String token : tokens) {
            int tokenStart = text.indexOf(token, lastIndex);
            int tokenEnd = tokenStart + token.length();

            if (tokenStart > lastIndex) spansBuilder.add(Collections.emptyList(), tokenStart - lastIndex);
            if (token.contains("@") && cli.isInCLI()) spansBuilder.add(Collections.singleton("cli"), token.length());
            else if (token.contains("=") && cli.isInCLI()) {
                int equalIndex = token.indexOf("=");
                spansBuilder.add(Collections.singleton("command"), equalIndex + 1);
                spansBuilder.add(Collections.singleton("value"), token.length() - (equalIndex + 1));
            } else spansBuilder.add(Collections.singleton("text"), token.length()); // anything that is not above

            lastIndex = tokenEnd;
        }

        if (lastIndex < text.length()) spansBuilder.add(Collections.emptyList(), text.length() - lastIndex);

        return spansBuilder.create();

    }

    public void setScreenCount(int screenCount) {
        for (int i = 1; i <= screenCount; i++) {
            MenuItem mi = new MenuItem("Monitor " + i);
            mi.setVisible(true);
            m_monitors.getItems().addAll(mi);
        }
    }

    @FXML
    public void testAction() {
        LOGGER.debug("Hello User!");
        api.test();
    }

    @FXML
    public void closeApp() {

        // TODO: todo save and then exit
        if (App.DEBUG) cl.log(this, "Closing App");
        api.closeApp();
    }

    public void setEntertainments(ArrayList<Entertainment> entertainments) {

        if (entertainments.size() <= 0) {
            cl.err(new Exception("Data List is <= 0"));

            noEpisodesPane.setPrefHeight(41);
            noEpisodesPane.setCenter(noEpisodesLabel);
            noEpisodesPane.getStyleClass().addAll("module", "no_episode_pane");
            noEpisodesLabel.getStyleClass().addAll("no_episode_label");
            noEpisodesLabel.setTextAlignment(TextAlignment.CENTER);

            list_view.getItems().add(noEpisodesPane);
            return;
        }

        listId = 0;
        for (Entertainment entertainment : entertainments) {
            if (entertainment instanceof Movie) {
                list_view.getItems().add(createModule((Movie) entertainment));
            } else if (entertainment instanceof Show) {
                list_view.getItems().add(createModule((Show) entertainment));
            } else {
                cl.err(new Exception("\"entertainment\" is an unknown instance"));
            }
        }
    }

    public BorderPane createModule(Movie movie) {
        FXMLPackage<BorderPane, MovieModuleController> mmp = FXMLFactory.getFxmlManager().getMovieModule();
        BorderPane mm = mmp.getPane();
        MovieModuleController mmc = mmp.getController();
        mmc.setId(++listId);
        mmc.setEntertainment(movie);
        return mm;
    }

    public BorderPane createModule(Show show) {

        FXMLPackage<BorderPane, ShowModuleController> smp = FXMLFactory.getFxmlManager().getShowModule();
        BorderPane sm = smp.getPane();
        ShowModuleController smc = smp.getController();
        smc.setId(++listId);
        smc.setEntertainment(show);

        smc.getModule().setTop(null);
        smc.getModule().setBottom(null);

        ArrayList<Episode> episodes = show.getEpisodes();
        episodes.sort(Comparator.comparing(Episode::getEpisodeNum));

        for (Episode episode : episodes) {
            smc.addEpisodeModule(episode);
        }

        return sm;
    }

    public BorderPane getModule() {
        return null;
    }

    @FXML
    public void minimizeApp() {
        Stage stage = (Stage) menubar.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void sortModulesByName() {
        if (App.DEBUG)
            cl.dbg("Sorting by Name");
        api.sortData(SortType.BY_NAME);
        list_view.getItems().clear();
        api.setFrontend();
    }

    @FXML
    public void sortModulesById() {
        if (App.DEBUG)
            cl.dbg("Sorting by Id");
        api.sortData(SortType.BY_ID);
        list_view.getItems().clear();
        api.setFrontend();
    }

    @FXML
    public void sortModulesByTypeTheName() {
        if (App.DEBUG)
            cl.dbg("Sorting by Type");
        api.sortData(SortType.BY_TYPE);
        list_view.getItems().clear();
        api.setFrontend();
    }

    /**
     * This is same as setViewerWidth() but this is used for the menuitem
     * mi_viewer to open/close the viewer.
     */
    @FXML
    public void setViewer() {
        if (viewerEnabled) {
            mi_viewer.setText("Open Viewer");
            viewerEnabled = true;
            hasSameData = true;
            setViewerWidth(0.24);
        } else {
            mi_viewer.setText("Close Viewer");
            viewerEnabled = false;
            hasSameData = false;
            setViewerWidth(0.24);
        }
    }

    /**
     * The viewer when has no data, will be closed. If a datamodule is clicked, the
     * viewer will open and show the data. if another datamodule is clicked, the
     * viewer will show the new data. if the same datamodule is clicked, then the
     * viewer will close.
     */
    @FXML
    public void setViewerWidth(double widthPercentage) {

        double width = api.getSceneManager().getStage().getWidth() * widthPercentage;

        if (viewerEnabled) {
            if (hasSameData) {
                mi_viewer.setText("Open Viewer");
                info_viewer_placeholder.setMinWidth(0);
                info_viewer_placeholder.setPrefWidth(0);
                splitPane.setDividerPosition(0, 0.0);
                info_viewer_placeholder.setManaged(false);
                info_viewer_placeholder.setVisible(false);
                info_viewer_placeholder.requestLayout();
                splitPane.requestLayout();
                viewerEnabled = false;
                hasSameData = false;
            } else {
                mi_viewer.setText("Close Viewer");
                info_viewer_placeholder.setMinWidth(width);
                info_viewer_placeholder.setPrefWidth(width);
                splitPane.setDividerPosition(0, 0.3);
                info_viewer_placeholder.setManaged(true);
                info_viewer_placeholder.setVisible(true);
                info_viewer_placeholder.requestLayout();
                splitPane.requestLayout();
                viewerEnabled = true;
            }

        } else {
            mi_viewer.setText("Close Viewer");
            info_viewer_placeholder.setMinWidth(width);
            info_viewer_placeholder.setPrefWidth(width);
            splitPane.setDividerPosition(0, 0.3);
            info_viewer_placeholder.setManaged(true);
            info_viewer_placeholder.setVisible(true);
            info_viewer_placeholder.requestLayout();
            splitPane.requestLayout();
            viewerEnabled = true;
        }

        LOGGER.info("Viewer width: " + info_viewer_placeholder.getPrefWidth());
        info_viewer_placeholder.requestLayout();
        LOGGER.debug("SameData: " + hasSameData + ", ViewerOpen: " + viewerEnabled);
    }

    public void viewEntertainment(Entertainment entertainment) throws EntertainmentTypeNotFoundException {

        this.currentEntertainment = entertainment;
        info_viewer_placeholder.getChildren().clear(); // remove all viewers

        if (App.DEBUG) cl.log(this, entertainment.getStageName());
        if (currentStageName == null && App.DEBUG) {
            cl.hlt("value null: \"currentStageName\"");
        }

        /*
         *
         * [FIXED]
         *
         * TOD-O: @start ViewerVisible Logic
         * TOD-O: rewrite this part of code
         * FIXM-E:
         *  - this part of the code only checks for stage names.
         *      but in shows, the stage name between many shows is the same
         *      with different season numbers.
         *  - When the show does not have a season name, the logic error
         *      happens.
         *
         * FIXM-E: Only change "currentStageName" and "hasSameData"
         *  - Without LogicError: StageName is not the same
         *          Lego Ninjago Dragons Rising: New World
         *          Lego Ninjago Dragons Rising: Another Season
         *  - With LogicError: The StageName is the same
         *          Lego Ninjago Dragons Rising (But this is referencing S1)
         *          Lego Ninjago Dragons Rising (But this is referencing S2)
         *
         * Old Logic:
         *  - Without LogicError: different stagename
         *          Lego Ninjago Dragons Rising: New World
         *          Lego Ninjago Dragons Rising: Another Season
         *  - With LogicError: both have same stagename
         *          Lego Ninjago Dragons Rising
         *          Lego Ninjago Dragons Rising
         *
         * New Logic: both not same.
         *  - Miraculous Ladybug S2
         *  - Miraculous Ladybug S6
         *
         * [The Fix] season number added to current stagename to differentiate between different
         *   season of the same show
         */
        if (currentStageName != null) {
            String newStageName = "";
            switch (entertainment.getType()) {
                case MOVIE:
                    newStageName = ((Movie) entertainment).getStageName();
                    LOGGER.info("Generated New StageName: " + newStageName);
                    break;
                case ANIME:
                case TVSHOW:
                    Show show = (Show) entertainment;
                    if (show.getTitle() == null || show.getTitle().isBlank())
                        newStageName = show.getStageName() + " S" + show.getSeasonNum();
                    else newStageName = show.getStageName();
                    LOGGER.info("Generated New StageName: " + newStageName);
                    break;
                case EPISODE:
                    newStageName = ((Episode) entertainment).getStageName();
                    LOGGER.info("Generated New StageName: " + newStageName);
                    break;
                default:
                    throw new EntertainmentTypeNotFoundException(entertainment.getType());
            }

            if (currentStageName.equals(newStageName)) {
                hasSameData = true;
                LOGGER.info("Same StageName: " + currentStageName);
                LOGGER.info("Current StageName: " + currentStageName);
                LOGGER.info("New StageName: " + newStageName);
            } else {
                hasSameData = false;
                LOGGER.info("Different Stage Name");
                LOGGER.info("Old StageName: " + currentStageName);
                LOGGER.info("New StageName: " + newStageName);
                currentStageName = newStageName;
            }
        }

        setViewerWidth(0.24);

        switch (entertainment.getType()) {
            case MOVIE:
                FXMLPackage<VBox, MovieViewerController> mvp = FXMLFactory.getFxmlManager().getMovieViewer();
                VBox mv = mvp.getPane();
                MovieViewerController mvc = mvp.getController();

                cl.log(this, "Viewer: " + mv);
                cl.log(this, "\nController: " + mvc);

                mv.setPrefWidth(info_viewer_placeholder.getWidth());
                info_viewer_placeholder.getChildren().addAll(mv);
                AnchorPane.setTopAnchor(mv, 0.0);
                AnchorPane.setBottomAnchor(mv, 0.0);
                AnchorPane.setLeftAnchor(mv, 0.0);
                AnchorPane.setRightAnchor(mv, 0.0);

                mvc.view(entertainment);
                mv.setVisible(true);
                break;

            case ANIME:
            case TVSHOW:

                FXMLPackage<VBox, ShowViewerController> svp = FXMLFactory.getFxmlManager().getShowViewer();
                VBox sv = svp.getPane();
                ShowViewerController svc = svp.getController();

                cl.log(this, "Viewer: " + sv);
                cl.log(this, "\nController: " + svc);

                sv.setPrefWidth(info_viewer_placeholder.getWidth());
                info_viewer_placeholder.getChildren().addAll(sv);
                AnchorPane.setTopAnchor(sv, 0.0);
                AnchorPane.setBottomAnchor(sv, 0.0);
                AnchorPane.setLeftAnchor(sv, 0.0);
                AnchorPane.setRightAnchor(sv, 0.0);

                svc.view(entertainment);
                sv.setVisible(true);
                break;
            case EPISODE:
                try {

                    FXMLPackage<VBox, EpisodeViewerController> evp = FXMLFactory.getFxmlManager().getEpisodeViewer();
                    VBox ev = evp.getPane();
                    EpisodeViewerController evc = evp.getController();

                    LOGGER.info("Viewer: " + ev);
                    LOGGER.info("Controller: " + evc);

                    ev.setPrefWidth(info_viewer_placeholder.getWidth());
                    info_viewer_placeholder.getChildren().addAll(ev);
                    AnchorPane.setTopAnchor(ev, 0.0);
                    AnchorPane.setBottomAnchor(ev, 0.0);
                    AnchorPane.setLeftAnchor(ev, 0.0);
                    AnchorPane.setRightAnchor(ev, 0.0);

                    Episode episode = (Episode) entertainment;
                    Show show = api.getShow(episode.getSeasonID());

                    evc.view(show, episode);
                    ev.setVisible(true);

                } catch (EntertainmentIdNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;

            default:
                throw new EntertainmentTypeNotFoundException(entertainment.getType());
        }

        if (App.DEBUG)
            cl.log(this, "Viewer viewing data");

    }

    public void resizeApp(double appWidthPercent, double appHeightPercent, double viewerWidthPercent) {
        api.getSceneManager().setStageSize(appWidthPercent, appHeightPercent);

        if (info_viewer_placeholder == null) return;

        info_viewer_placeholder.setPrefHeight(Screen.getPrimary().getBounds().getHeight() - 800);
        info_viewer_placeholder.setMinHeight(Screen.getPrimary().getBounds().getHeight() - 800);
        cl.log(this, "App: width  = " + api.getSceneManager().getStage().getWidth() + ", height = " + api.getSceneManager().getStage().getHeight());
        setViewerWidth(viewerWidthPercent);
        try {
            hasSameData = false;
            if (currentEntertainment == null) return;
            viewEntertainment(currentEntertainment);
        } catch (EntertainmentTypeNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public AnchorPane getViewerPlaceholder() {
        return info_viewer_placeholder;
    }

    @FXML
    private void runCommandLine() {
        // api.runCmdLine();
    }

    public void debrief(String command) {

    }

    public void setCliView() {
        homebase.setCenter(ta);
        homebase.setBottom(scta);
    }

    public void resetMainUI() {
        homebase.setCenter(splitPane);
    }

    public void showEntertainmentInCli(Entertainment entertainment) {
        ta.setText(
                ta.getText() + "\n" +
                        entertainment.getType() + "\n" +
                        entertainment.getFranchise() + "\n" +
                        entertainment.getTitle() + "\n" +
                        entertainment.getVisualDate() + "\n" +
                        "\n"

        );
    }

    public void showMessageInCli(String string) {
    }
}
