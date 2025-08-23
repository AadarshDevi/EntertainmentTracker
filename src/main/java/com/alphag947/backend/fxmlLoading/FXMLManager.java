package com.alphag947.backend.fxmlLoading;

import com.alphag947.backend.entertainment.Movie;
import com.alphag947.backend.fxmlLoading.exception.FXMLNullException;
import com.alphag947.controller.ParentController;
import com.alphag947.controller.TestController;
import com.alphag947.controller.entertainmentViewer.dataModule.EpisodeModuleController;
import com.alphag947.controller.entertainmentViewer.dataModule.MovieModuleController;
import com.alphag947.controller.entertainmentViewer.dataModule.ShowModuleController;
import com.alphag947.controller.entertainmentViewer.dataViewer.EpisodeViewerController;
import com.alphag947.controller.entertainmentViewer.dataViewer.MovieViewerController;
import com.alphag947.controller.entertainmentViewer.dataViewer.ShowViewerController;
import com.alphag947.controller.uiController.EditorController;
import com.alphag947.controller.uiController.MainframeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;

/**
 * The FXMLManager makes managing fxml files easier because all the fxml reading is done in the
 * same class. Whenever a new fxml needs to be read, it can be placed in this class where it will
 * be retrievable by other classes.
 *
 * @param <T> It is a class that extends the Pane class. All objects like VBox, BorderPane,
 *            AnchorPane are subclasses of this. This helps to because the object can be
 *            any of these.
 * @param <C> It is a class that extends the ParentController. All controllers are children
 *            of this.
 * @author AlpharinoGamer947
 * @see FXMLFactory
 * @see FXMLPackage
 */
public class FXMLManager<T extends Pane, C extends ParentController> {

    /**
     * This is the root of the old fxml files. the files are in the root folder,
     * aka the resources' folder. this helps to remove redundancy of typing
     * the same part of the filepath.
     */
    private static final String FXML_BASEPATH = "/com/alphag947/v1/fxml/";

    /**
     * This is the redundant filepath of the new fxml files.
     */
    @SuppressWarnings("unused")
    private static final String FXML_BASEPATH2 = "/com/alphag947/v2/fxml/";

    /**
     * The Logger for the FXMLManager class
     *
     * @see Logger
     */
    private final Logger LOGGER = Logger.getLogger(FXMLManager.class);

    /**
     * An empty constructor
     */
    public FXMLManager() {
    }

    /**
     * This reads fxml files and gets the ui, the borderpane, anchorpane, etc. after
     * getting the ui part of the fxml, it will get the controller of the fxml. The
     * pane and controller are bundled up into a package called the FXMLPackage. The
     * Pane and Controller are later cast into their appropriate Panes and Controllers.
     *
     * @param filepath the filepath to the fxml file from the root folder (resources folder)
     * @return return a FXMLPackage.
     * @see FXMLPackage
     */
    private FXMLPackage<T, C> readFXML(String filepath) {
        try {
            URL url = FXMLManager.class.getResource(filepath);
            if (url == null)
                throw new URLNullException(filepath);
            FXMLLoader loader = new FXMLLoader(url);
            LOGGER.info("FXMLLoader: " + loader);
            T pane = loader.load();
            LOGGER.info("Pre-Pane: " + pane);
            C controller = loader.getController();
            LOGGER.info("Pre-Package Controller: " + controller);
            pane.getProperties().put("controller", controller);
            if (controller == null)
                throw new ControllerNullException(filepath);
            return new FXMLPackage<>(pane, controller);
        } catch (URLNullException | IOException | ControllerNullException e) {
            LOGGER.error(e);
        }
        return null;
    }

    /**
     * This is the old UI for the app.
     *
     * @return returns fxml package containing mainframe ui and controller
     * @throws FXMLNullException throws if fxmlpackage is null
     * @ui OldUI.Main.MainFrameUI
     * @see FXMLPackage
     * @see MainframeController
     */
    @SuppressWarnings("unchecked")
    public FXMLPackage<BorderPane, MainframeController> getMainframe() throws FXMLNullException {
        return (FXMLPackage<BorderPane, MainframeController>) readFXML(FXML_BASEPATH + "main/mainframe_b2_v1.fxml");
    }

    /**
     * FIXME: NewUI.base.v1 is not getting read
     * <p>
     * This is the new ui for the home screen of the app. this is also the new default
     * screen for the app.
     * <p>
     * The FXMLPackage is for NewUI.base.v1 because it is targeting the v1 fxml
     * <br>
     * The UI is for NewUI.base.v1 because it has the base.v1 ui
     * <br>
     * The Controller is for NewUI.base because it is targeting base since all base.vx will
     * have the same controller but different ui
     *
     * @return new UI for home screen of app
     * @ui NewUI.base.v1
     */
    public FXMLPackage<BorderPane, TestController> getTestFrame() throws FXMLNullException {
        System.out.println(
                "--------------------------------------------" +
                        "--------------------------------------------" +
                        "--------------------------------------------" +
                        "--------------------------------------------"
        );
        LOGGER.debug("Getting TestFrame...");
        FXMLPackage<T, C> fp = readFXML("/com/alphag947/v2/fxml/base/base_v1.fxml");
        if (fp == null) {
            LOGGER.error("NewUI FXMLPackage is null");
            throw new FXMLNullException("FXMLPackage null: NewUI.Base.v1");
        }

        BorderPane bp = (BorderPane) fp.getPane();
        if (bp == null) {
            LOGGER.error("NewUI.Base.v1 is null");
            throw new RuntimeException("UI null: NewUI.Base.v1");
        }

        TestController tc = (TestController) fp.getController();
        if (tc == null) {
            LOGGER.error("NewController.Base is null");
            throw new RuntimeException("Controller null: NewUI.Base");
        }
        return new FXMLPackage<>(bp, tc);
    }

    // ModuleUI Elements

    /**
     * @return return the MovieModule
     * @ui OldUI.Data.Viewer.ModuleViewer.MovieModule
     * @see MovieModuleController
     * @see Movie
     */
    @SuppressWarnings({"unchecked"})
    public FXMLPackage<BorderPane, MovieModuleController> getMovieModule() {
        return (FXMLPackage<BorderPane, MovieModuleController>) readFXML(
                FXML_BASEPATH + "entertainmentViewer/moduleViewer/movie_module_b2_v1.fxml");
    }

    @SuppressWarnings({"unchecked", "unused"})
    public FXMLPackage<BorderPane, EditorController> getEditor() {
        return (FXMLPackage<BorderPane, EditorController>) readFXML(
                FXML_BASEPATH + "main/editor_b2_v1.fxml");
    }

    @SuppressWarnings("unchecked")
    public FXMLPackage<BorderPane, ShowModuleController> getShowModule() {
        return (FXMLPackage<BorderPane, ShowModuleController>) readFXML(
                FXML_BASEPATH + "entertainmentViewer/moduleViewer/show_module_b2_v1.fxml");
    }

    @SuppressWarnings("unchecked")
    public FXMLPackage<BorderPane, EpisodeModuleController> getEpisodeModule() {
        return (FXMLPackage<BorderPane, EpisodeModuleController>) readFXML(
                FXML_BASEPATH + "entertainmentViewer/moduleViewer/episode_module_b2_v1.fxml");
    }

    // ViewerUI Elements

    /**
     * @return MovieViewer
     * @ui OldUI.Data.Viewer.ModuleViewer.MovieViewer
     * @see MovieViewerController
     * @see Movie
     */
    @SuppressWarnings("unchecked")
    public FXMLPackage<VBox, MovieViewerController> getMovieViewer() {
        return (FXMLPackage<VBox, MovieViewerController>) readFXML(
                FXML_BASEPATH + "entertainmentViewer/infoViewer/movie_viewer_v1.fxml");
    }

    /**
     * @return TVShowViewer
     * @ui OldUI.Data.Viewer.ModuleViewer.TVShowViewer
     * @see MovieViewerController
     * @see Movie
     */
    @SuppressWarnings("unchecked")
    public FXMLPackage<VBox, ShowViewerController> getShowViewer() {
        return (FXMLPackage<VBox, ShowViewerController>) readFXML(
                FXML_BASEPATH + "entertainmentViewer/infoViewer/show_viewer_v1.fxml");
    }

    public FXMLPackage<VBox, EpisodeViewerController> getEpisodeViewer() {
        return (FXMLPackage<VBox, EpisodeViewerController>) readFXML(
                FXML_BASEPATH + "entertainmentViewer/infoViewer/episode_viewer_v1.fxml");
    }
}