package com.alphag947.backend.fxmlLoading.exception;

public class FXMLNullException extends FXMLNotFoundException {

    public FXMLNullException() {
        super("FXML at given filepath not found.");
    }
    public FXMLNullException(String filepath) {
        super("FXML at filepath \"" + filepath + "\" not found.");
    }
}
