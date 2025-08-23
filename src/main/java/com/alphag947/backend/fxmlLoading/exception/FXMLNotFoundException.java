package com.alphag947.backend.fxmlLoading.exception;

import java.io.File;

public class FXMLNotFoundException extends Exception {

    public FXMLNotFoundException(File file) {
        super("FXML at filepath \"" + file.getPath() + "\" not found.");
    }

    public FXMLNotFoundException(String message) {
        super(message);
    }
}
