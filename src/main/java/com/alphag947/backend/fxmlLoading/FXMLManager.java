package com.alphag947.backend.fxmlLoading;

import java.io.IOException;

import com.alphag947.backend.logging.ConsoleLogger;
import com.alphag947.backend.logging.LoggerFactory;
import com.alphag947.controller.ParentController;
import com.alphag947.controller.entertainmentViewer.moduleViewer.EpisodeModuleController;
import com.alphag947.controller.entertainmentViewer.moduleViewer.MovieModuleController;
import com.alphag947.controller.entertainmentViewer.moduleViewer.ShowModuleController;
import com.alphag947.controller.uiController.EditorController;
import com.alphag947.controller.uiController.MainframeController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class FXMLManager<T extends BorderPane, C extends ParentController> {

    private ConsoleLogger cl;

    // setting up logger
    public FXMLManager() {
        cl = LoggerFactory.getConsoleLogger();
    }

    // reading fxmls
    private FXMLPackage<T, C> readFXML(String filepath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(filepath));
            T pane = loader.load();
            C controller = loader.getController();
            pane.getProperties().put("controller", controller);
            if (pane == null || controller == null) {
                cl.err(this, new IOException("FXMLPackage object is null"));
                System.exit(0);
            }
            // controller.set
            return new FXMLPackage<T, C>(pane, controller);
        } catch (IOException e) {
            cl.err(this, e);
            e.getStackTrace();
        }
        cl.err(this, new Exception("FXMLPackage is null!"));
        return null;
    }

    @SuppressWarnings("unchecked")
    public FXMLPackage<BorderPane, MainframeController> getMainframe() {
        return (FXMLPackage<BorderPane, MainframeController>) readFXML(
                "/com/alphag947/fxml/mainui/mainframe_b2_v1.fxml");
    }

    public void getMovieViewer() {
    }

    @SuppressWarnings("unchecked")
    public FXMLPackage<BorderPane, MovieModuleController> getMovieModule() {
        return (FXMLPackage<BorderPane, MovieModuleController>) readFXML(
                "/com/alphag947/fxml/entertainmentViewer/moduleViewer/movie_module_b2_v1.fxml");
    }

    @SuppressWarnings("unchecked")
    public FXMLPackage<BorderPane, EditorController> getEditor() {
        return (FXMLPackage<BorderPane, EditorController>) readFXML(
                "/com/alphag947/fxml/mainui/editor_b2_v1.fxml");
    }

    @SuppressWarnings("unchecked")
    public FXMLPackage<BorderPane, ShowModuleController> getShowModule() {
        return (FXMLPackage<BorderPane, ShowModuleController>) readFXML(
                "/com/alphag947/fxml/entertainmentViewer/moduleViewer/show_module_b2_v1.fxml");
    }

    public void getShowViewer() {
    }

    @SuppressWarnings("unchecked")
    public FXMLPackage<BorderPane, EpisodeModuleController> getEpisodeModule() {
        return (FXMLPackage<BorderPane, EpisodeModuleController>) readFXML(
                "/com/alphag947/fxml/entertainmentViewer/moduleViewer/episode_module_b2_v1.fxml");
    }

}
