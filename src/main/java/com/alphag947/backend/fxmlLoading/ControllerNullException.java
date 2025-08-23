package com.alphag947.backend.fxmlLoading;

public class ControllerNullException extends Throwable {
    public ControllerNullException(String filepath) {
        super("Controller for URL not found: " + filepath);
    }
}
