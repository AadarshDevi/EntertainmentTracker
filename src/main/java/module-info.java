module com.alphag947 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.alphag947.backend.fxmlLoading to javafx.fxml;
    opens com.alphag947.controller.uiController to javafx.fxml;
    opens com.alphag947.controller.entertainmentViewer.moduleViewer to javafx.fxml;

    exports com.alphag947;
}
