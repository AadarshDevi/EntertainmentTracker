package com.alphag947.SceneFactory.exception;

public class SceneHistoryEmptyException extends Exception {

    public SceneHistoryEmptyException() {
        super("SceneHistory is Empty");
    }

    public SceneHistoryEmptyException(String message) {
        super(message);
    }

}
