module com.alphag947 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.desktop;
    requires log4j;
    requires org.fxmisc.richtext;

    exports com.alphag947.controller to javafx.fxml;

    opens com.alphag947.backend.fxmlLoading to javafx.fxml;
    opens com.alphag947.controller.uiController to javafx.fxml;
    opens com.alphag947.controller.entertainmentViewer.dataModule to javafx.fxml;
    opens com.alphag947.controller.entertainmentViewer.dataViewer to javafx.fxml;

    exports com.alphag947;
}
